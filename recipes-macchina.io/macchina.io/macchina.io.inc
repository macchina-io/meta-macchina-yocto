LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=136e4f49dbf29942c572a3a8f6e88a77"

SRC_URI = "git://github.com/macchina-io/macchina.io.git;protocol=https;nobranch=1 \
           file://0003-allow-external-Poco-build.patch \
           file://0004-fix-HAVE_MEMMOVE-define.patch \
           file://0005-Add-cortexm8-support-to-V8.patch \
           "

PV = "0.10.0+git${SRCPV}"
SRCREV = "9c28a0a0d6254771797fffc39b98e2e6bdef29fa"

S = "${WORKDIR}/git"
