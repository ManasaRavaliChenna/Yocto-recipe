LICENSE = "CLOSED"

#
# This is a template recipes. It helps developers to test codes quickly.
# 1. Just copy codes into "files" directory.
# 2. build/compile codes by running "bitbake hello-cortina" in build directory
# 3. If you would like add it in rootfs, add following text in conf/local.conf
#    CORE_IMAGE_EXTRA_INSTALL +=  "hello-cortina"
#

S = "${WORKDIR}/git/"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI="git://github.com/ManasaRavaliChenna/TestGit.git;protocol=https"
SRC_URI_append = " file://0001-patching.patch"
SRCREV = "0d54cc551cd051eb4bfba7cc7c8115523a926d12"

#inherit autotools
FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

#EXTRA_OEMAKE = 'all -C ${S}'
#EXTRA_OEMAKE = "'CC=${CC}'"
do_compile () {
	#${CC} -o ${S}sample-hello ${S}main.c ${S}addition.c ${S}subtraction.c ${S}multiplication.c
	make all -C ${S} 
}

do_install () {
	install -d ${D}/${bindir}
	install -m 0755 ${S}sample-hello ${D}${bindir}
}


CORE_IMAGE_EXTRA_INSTALL +=  "sample-hello"
