cmake_minimum_required(VERSION 3.22.1)
project(kffi_engine LANGUAGES C)

include(FetchContent)
FetchContent_Declare(
    libffi
    GIT_REPOSITORY https://github.com/libffi/libffi.git
    GIT_TAG v3.4.6
    GIT_SHALLOW TRUE
    SOURCE_SUBDIR build
)
FetchContent_MakeAvailable(libffi)

include("${CMAKE_CURRENT_SOURCE_DIR}/libffi_glue.cmake")

add_library(kffi_bench_fixture SHARED bench_engine_fixture.c)
target_link_libraries(kffi_bench_fixture PRIVATE log)

add_library(kffi SHARED kffi_engine.c)
target_link_libraries(kffi PRIVATE log ffi)
