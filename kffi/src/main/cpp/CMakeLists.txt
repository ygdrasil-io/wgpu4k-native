cmake_minimum_required(VERSION 3.22.1)
project(kffi_engine LANGUAGES C)

add_library(kffi_bench_fixture SHARED bench_engine_fixture.c)
target_link_libraries(kffi_bench_fixture PRIVATE log)

add_library(kffi SHARED kffi_engine.c)
target_link_libraries(kffi PRIVATE kffi_bench_fixture log)
