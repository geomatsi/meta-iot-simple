DESCRIPTION = "IoT packages"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = "\
    pcduino-tools \
    mosquitto \
    "
