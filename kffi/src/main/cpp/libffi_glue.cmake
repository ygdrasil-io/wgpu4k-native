# libffi_glue.cmake — build libffi v3.4.6 from the FetchContent-populated source,
# without autotools. Produces a static libffi.a that is linked into libkffi.so.
#
# The libffi source tree is NOT buildable by CMake (it uses autotools and ships
# only .in templates). This glue hand-crafts the two generated headers
# (ffi.h from include/ffi.h.in, fficonfig.h) for the Android NDK and compiles
# the common + per-arch sources directly.
if(TARGET ffi)
  return()
endif()

enable_language(ASM)

set(ffi_src_dir "${libffi_SOURCE_DIR}/src")
set(ffi_gen_dir "${CMAKE_CURRENT_BINARY_DIR}/libffi-gen")
file(MAKE_DIRECTORY "${ffi_gen_dir}")

# Select the per-arch source set and generated-header config.
if(CMAKE_SYSTEM_PROCESSOR MATCHES "aarch64|arm64")
  set(ffi_machine aarch64)
elseif(CMAKE_SYSTEM_PROCESSOR MATCHES "^(arm|armv|armv7)")
  set(ffi_machine arm)
elseif(CMAKE_SYSTEM_PROCESSOR MATCHES "x86_64|amd64")
  set(ffi_machine x86_64)
elseif(CMAKE_SYSTEM_PROCESSOR MATCHES "i386|i686|x86$")
  set(ffi_machine x86)
else()
  message(FATAL_ERROR "kffi: unsupported libffi target processor: ${CMAKE_SYSTEM_PROCESSOR}")
endif()

set(ffi_common_sources
  "${ffi_src_dir}/prep_cif.c"
  "${ffi_src_dir}/types.c"
  "${ffi_src_dir}/raw_api.c"
  "${ffi_src_dir}/java_raw_api.c"
  "${ffi_src_dir}/closures.c"
  "${ffi_src_dir}/tramp.c"
)

if(ffi_machine STREQUAL "aarch64")
  set(ffi_arch_sources
    "${ffi_src_dir}/aarch64/ffi.c"
    "${ffi_src_dir}/aarch64/sysv.S"
  )
  set(ffi_target_macro AARCH64)
  set(ffi_have_long_double 1)
  set(ffi_arch_dir aarch64)
elseif(ffi_machine STREQUAL "arm")
  set(ffi_arch_sources
    "${ffi_src_dir}/arm/ffi.c"
    "${ffi_src_dir}/arm/sysv.S"
  )
  set(ffi_target_macro ARM)
  set(ffi_have_long_double 0)
  set(ffi_arch_dir arm)
elseif(ffi_machine STREQUAL "x86_64")
  set(ffi_arch_sources
    "${ffi_src_dir}/x86/ffi64.c"
    "${ffi_src_dir}/x86/unix64.S"
    "${ffi_src_dir}/x86/ffiw64.c"
    "${ffi_src_dir}/x86/win64.S"
    "${ffi_src_dir}/x86/ffi.c"
    "${ffi_src_dir}/x86/sysv.S"
  )
  set(ffi_target_macro X86_64)
  set(ffi_have_long_double 1)
  set(ffi_arch_dir x86)
else()
  set(ffi_arch_sources
    "${ffi_src_dir}/x86/ffi.c"
    "${ffi_src_dir}/x86/sysv.S"
  )
  set(ffi_target_macro X86)
  set(ffi_have_long_double 0)
  set(ffi_arch_dir x86)
endif()

# Generate ffi.h from the shipped template. The @TARGET@ substitution is what
# defines the AARCH64/ARM/X86_64/X86 macro that the arch ffitarget.h relies on.
set(FFI_VERSION "3.4.6")
set(TARGET "${ffi_target_macro}")
set(HAVE_LONG_DOUBLE "${ffi_have_long_double}")
# Android (non-Mach) cannot use trampoline tables; must stay 0. Closures are
# enabled instead via FFI_MMAP_EXEC_WRIT + FFI_EXEC_STATIC_TRAMP in the
# hand-crafted fficonfig.h.in (matching libffi's *-linux-android* configure).
set(FFI_EXEC_TRAMPOLINE_TABLE 0)
configure_file(
  "${libffi_SOURCE_DIR}/include/ffi.h.in"
  "${ffi_gen_dir}/ffi.h"
  @ONLY
)

# Hand-crafted fficonfig.h mirroring configure for the Android NDK. The
# FFI_HIDDEN block is what autoconf's AH_BOTTOM appends to config.h.
if(ffi_machine STREQUAL "x86_64" OR ffi_machine STREQUAL "x86")
  # LLVM's integrated assembler rejects the `@rel` relocation variant; this
  # makes the x86 unwind tables use the PC-relative `X - .` form instead.
  set(HAVE_AS_X86_PCREL 1)
else()
  set(HAVE_AS_X86_PCREL 0)
endif()
if(ffi_machine STREQUAL "x86")
  # src/x86/sysv.S references EH_FRAME_FLAGS in its .eh_frame section
  # directive; configure defines it via the EH_FRAME_FLAGS AC_DEFINE.
  set(EH_FRAME_FLAGS_DEFINE [[#define EH_FRAME_FLAGS "a"]])
else()
  set(EH_FRAME_FLAGS_DEFINE "")
endif()
configure_file(
  "${CMAKE_CURRENT_LIST_DIR}/libffi_fficonfig.h.in"
  "${ffi_gen_dir}/fficonfig.h"
  @ONLY
)

add_library(ffi STATIC
  ${ffi_common_sources}
  ${ffi_arch_sources}
)

target_include_directories(ffi PUBLIC
  "${ffi_gen_dir}"
  "${libffi_SOURCE_DIR}/include"
  "${libffi_SOURCE_DIR}/src/${ffi_arch_dir}"
)

target_compile_options(ffi PRIVATE
  -fPIC
  -fvisibility=hidden
)

# The arch assembly and C sources gate themselves on compiler macros
# (__aarch64__, __arm__, __i386__, __x86_64__) plus the target macro from
# ffi.h; no extra -D is required.

# libffi sources use GNU inline asm (`asm` keyword); the module's global
# -std=c11 flag would reject it. Override back to gnu11 for the ffi target
# (later -std wins in clang).
target_compile_options(ffi PRIVATE -std=gnu11)
