cmake_minimum_required(VERSION 3.22.1)
project(kffi_engine LANGUAGES C)

# Share one FetchContent checkout across all 4 ABI configure runs instead of
# cloning per-ABI under .cxx. kffi/.gradle is gitignored (`.gradle` matches at
# any depth), so the shared cache is never committed. This MUST be set before
# include(FetchContent): that module itself defines FETCHCONTENT_BASE_DIR as a
# cache var (${CMAKE_BINARY_DIR}/_deps, i.e. per-ABI) at include time, which
# would otherwise defeat the shared base dir.
if(NOT DEFINED FETCHCONTENT_BASE_DIR)
  set(FETCHCONTENT_BASE_DIR "${CMAKE_CURRENT_SOURCE_DIR}/../../../.gradle/fetchcontent" CACHE PATH "Shared FetchContent base dir")
endif()

include(FetchContent)

# No SOURCE_SUBDIR: upstream libffi ships no CMakeLists.txt, so
# FetchContent_MakeAvailable's `if(EXISTS <src>/CMakeLists.txt)` guard skips
# add_subdirectory on its own; the glue below is what builds libffi.
FetchContent_Declare(
    libffi
    GIT_REPOSITORY https://github.com/libffi/libffi.git
    GIT_TAG 3d0ce1e6fcf19f853894862abcbac0ae78a7be60 # libffi v3.4.6 (@3d0ce1e6)
    GIT_SHALLOW FALSE
)
FetchContent_MakeAvailable(libffi)

include("${CMAKE_CURRENT_SOURCE_DIR}/libffi_glue.cmake")

add_library(kffi_bench_fixture SHARED bench_engine_fixture.c)
target_link_libraries(kffi_bench_fixture PRIVATE log)

add_library(kffi SHARED kffi_engine.c kffi_upcall.c)
target_link_libraries(kffi PRIVATE log ffi)
