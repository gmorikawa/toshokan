# Service: Minio

## Setting up a Debian environment with MinIO

To make the first test using MinIO I created a Debian 12 virtual machine on Proxmox and did the following steps:

* Configured a static IPv4 address to the VM instance;
* Plugged (virtually) a USB device to the VM instance;
* Installed the packages from MinIO using `apt`;
* Make drive mount on startup;
* Created a daemon service for MinIO and made it start automatically;

### Configured a static IPv4 address to the VM instance

To define a static IPv4, I edited the file `/etc/network/interfaces`, which holds the configuration of all network interfaces. The first thing to do was to identify which interface was the primary network interface. In my case the interface was named `ens18` and had the following configuration:

```conf
allow-hotplug ens18
iface ens18 inet dhcp
```

See that at the end of the second line there is `dhcp`, which tells that the machine will be addressed with a IPv4 automatically when connecting to the router. Change the `dhcp` for `static` and in the following lines provide the necessary information to connect to the network with a defined IP address:

```conf
allow-hotplug ens18
iface ens18 inet static
    address 192.168.0.120
    netmask 255.255.255.0
    gateway 192.168.0.1
    dns-nameservers 1.1.1.1 8.8.8.8
```

I am not a specialist in networks, but this is the configuration of my home network. All my machines have the same netmask and gateway, meaning that they are all in the same network, and, therefore, can communicate between them.

### Plugged (virtually) a USB device to the VM instance

In the Hardware configuration of my VM in Proxmox I added a passthrough to a USB device by using the option _Use USB Vendor/Device ID_ and selecting the desired USB device connected to my server.

Then, in the VM it will be possible to mount and use this device.

### Installed the packages from MinIO using `apt`

As defined in the MinIO documentation, I executed the following commands as a __root__ user:

```sh
wget https://dl.min.io/server/minio/release/linux-amd64/minio_20250422221226.0.0_amd64.deb
dpkg -i minio_20250422221226.0.0_amd64.deb
```

### Make drive mount on startup

The USB device can be mount on the system, but still it requires to be manually setup.

To configure the drive to start automatically, it is necessary to modify the `/etc/fstab` file and add a line with the device configuration:

```conf
...

UUID=<id>   /media/hdd  ext4    defaults,nofail,x-systemd.device-timeout=30 0 0
```

### Created a daemon service for MinIO and made it start automatically

First, created a `minio.service` file in `/etc/systed/sytem/` directory. Inside this directory I have the following information:

```conf
[Unit]
Description=MinIO Bucket Storage
After=network.target
StartLimitIntervalSec=0

[Service]
Type=simple
Restart=always
RestartSec=1
User=gmorikawa
Environment=MINIO_ROOT_USER=admin
Environment=MINIO_ROOT_PASSWORD=minioroot
ExecStart=/usr/ocal/bin/minio server /media/hdd --console-address ":9001"

[Install]
WantedBy=multi-user.target
```

After defining this file it is necessary to enable the service start from the startup of the OS:

```sh
systemctl enable minio
```
