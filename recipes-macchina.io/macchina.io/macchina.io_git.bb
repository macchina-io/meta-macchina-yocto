require macchina.io.inc

SRC_URI += "file://macchina-io \
            file://macchina.properties"

DEPENDS = "openssl macchina.io-hosttools-native poco"

do_configure () {
    # Nothing to configure
    :
}


export POCO_TARGET_OSNAME="Linux"
export POCO_TARGET_OSARCH="${TUNE_PKGARCH}"
export CROSS_COMPILE="${TARGET_PREFIX}"

# These are provided by macchina.io-hosttools-native
export BUNDLE_TOOL="bundle"
export PAGE_COMPILER="cpspc"

# Use system supplied POCO
export POCO_EXTERNAL="1"

# Does not work with cross compilation right now
#export V8_ENABLE_SNAPSHOT="1"

# There are race conditions with parallel make that leads to file not found errors
PARALLEL_MAKE = ""

do_compile () {
    #Needs to be set for the native bundler to find it's libraries
    export LD_LIBRARY_PATH="${STAGING_LIBDIR_NATIVE}"
    oe_runmake CC="${CC}" CLFAGS="-Isrc ${CFLAGS}" CXXFLAGS="-Isrc -DPOCO_TARGET_OSARCH=\"\\\"${POCO_TARGET_OSARCH}\\\"\" ${CXXFLAGS}" CXX="${CXX}" LINKFLAGS="${LDFLAGS}" DEFAULT_TARGET=shared_release
}

do_install () {
    install -d ${D}/opt/macchina
    oe_runmake install_runtime INSTALLDIR="${D}/opt/macchina"

    # Override configuration
    install -m 0644 ${WORKDIR}/macchina.properties ${D}/opt/macchina/etc/

    # Add wrapper
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/macchina-io ${D}${bindir}
}

FILES_${PN} += "/opt"

#TODO: Make macchina.io not strip the resulting libraries
INSANE_SKIP_${PN} = "already-stripped"
