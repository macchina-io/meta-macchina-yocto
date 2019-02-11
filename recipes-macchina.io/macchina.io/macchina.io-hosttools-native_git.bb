require macchina.io.inc

DEPENDS = "openssl-native"

inherit native

do_configure () {
    # Nothing to configure
    :
}

export POCO_TARGET_OSNAME="Linux"
export POCO_TARGET_OSARCH="${BUILD_ARCH}"

do_compile () {
    oe_runmake hosttools DEFAULT_TARGET=shared_release
}

do_install () {
    install -d ${D}${bindir}
    install -d ${D}${libdir}
    install -D -m 0755 ${S}/platform/OSP/BundleCreator/bin/Linux/${POCO_TARGET_OSARCH}/bundle ${D}${bindir}/
    install -D -m 0755 ${S}/platform/PageCompiler/bin/Linux/${POCO_TARGET_OSARCH}/cpspc ${D}${bindir}/
    install -D -m 0755 ${S}/platform/PageCompiler/File2Page/bin/Linux/${POCO_TARGET_OSARCH}/f2cpsp ${D}${bindir}/
    cp -R ${S}/platform/lib/Linux/${POCO_TARGET_OSARCH}/* ${D}${libdir}/

    # TODO: Add other hosttools
}
