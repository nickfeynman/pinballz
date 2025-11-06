#!/bin/bash

# Configuration
API_BASE_URL="https://app.matchplay.events/api"
TOURNAMENT_ID=160189 #  harvest tournament id
ARENA_ID=154983  # whirlwind
API_TOKEN="310|zLxG2hIAN6mIEQDilxRAiyVy2mzj3Do3Q7nBSemNa5893106"  

# Make the API request
curl --request GET \
  --url "${API_BASE_URL}/tournaments/${TOURNAMENT_ID}/single-player-games?arena=${ARENA_ID}&sortBy=score" \
  --header "Authorization: Bearer ${API_TOKEN}" \
  --header "Content-Type: application/json" \
  --header "Accept: application/json" \
  --header "Cache-Control: no-cache, no-store, must-revalidate" \
  --header "Pragma: no-cache" \
  --header "Expires: 0" \
  --compressed \
    | jq '.'  # Pretty print the JSON



