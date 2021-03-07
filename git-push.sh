#!/usr/bin/env bash

# capture last git message
message=$(git log -1 --oneline)
# trim off git ref (first 8 chars)
message=${message:8}

echo "message captured: ${message}"

#./build-exec.sh
#git add -f target/constructor-1.0.0-SNAPSHOT-runner

git add .
git cmt "temp commit"
git reset --soft HEAD~2
git cmt "${message}"

#git cmt "fix-up"
#git reset --soft HEAD~2
#git cmt $message
#git push
