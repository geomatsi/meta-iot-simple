DESCRIPTION = "IoT packages"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = "\
    nrf24-tools \
    mosquitto \
    avahi-daemon \
    avahi-utils \
    lrzsz \
    ntp \
    "
