DESCRIPTION = "Embedded web packages"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = "\
    node \
    node-sensor-test \
    node-red \
    "
