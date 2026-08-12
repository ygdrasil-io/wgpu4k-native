cmake_minimum_required(VERSION 3.22.1)
project(kffi_engine LANGUAGES C)

add_library(kffi SHARED kffi_engine.c)
target_link_libraries(kffi PRIVATE log)
