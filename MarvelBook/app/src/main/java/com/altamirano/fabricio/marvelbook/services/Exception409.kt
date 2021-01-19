package com.altamirano.fabricio.marvelbook.services

class Exception409() : Throwable("Missing API Key | Missing Hash | Missing Timestamp", Throwable("Occurs when the apikey parameter is not included with a request."))