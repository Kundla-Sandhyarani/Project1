#!/bin/bash
/opt/dependency-check/bin/dependency-check.sh \
  --project Project1 \
  --scan . \
  --format HTML \
  --out dependency-report.html
