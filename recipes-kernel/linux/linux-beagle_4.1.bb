require recipes-kernel/linux/linux-yocto.inc

COMPATIBLE_MACHINE = "beaglebone"

DEPENDS += "lzop-native"

LINUX_VERSION= "4.1.6"
LINUX_VERSION_EXTENSION = "-iot"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-beagle-4.1:"

KBRANCH_beaglebone = "4.1"

PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r0"

SRCREV_beaglebone ?= "b50f36961b02bbe6eea04e5a2a472695a84d26b3"

SRC_URI = "git://github.com/beagleboard/linux.git;branch=${KBRANCH};protocol=git \
        file://0000-nrf24-spidev-dts.patch \
        file://defconfig \
        "

