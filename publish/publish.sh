#!/bin/bash

echo $GPG_KEY_CONTENTS | base64 -d > /secret.gpg
gradlew eoandroidconcurrency:publishReleasePublicationToSonatypeRepository
gradlew closeAndReleaseRepository
