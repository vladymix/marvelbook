package com.altamirano.fabricio.marvelbook.services

class Exception401() : Throwable("Invalid Referer | Invalid Hash", Throwable("Occurs when a referrer which is not valid for the passed apikey parameter is sent. or Occurs when a ts, hash and apikey parameter are sent but the hash is not valid per the above hash generation rule."))