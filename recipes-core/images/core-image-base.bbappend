# IoT software
IMAGE_INSTALL_append = " \
    packagegroup-iot \
	"

# dev tools
IMAGE_INSTALL_append = " \
    packagegroup-core-tools-debug \
    packagegroup-core-ssh-dropbear \
    "
