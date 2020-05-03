import sys
import argparse
import json
import requests

parser = argparse.ArgumentParser()
parser.add_argument(
  "--token", 
  help="Bitrise Access Token"
)
parser.add_argument(
  "--appSlug", 
  help="Application slug"
)
parser.add_argument(
  "--workflowId", 
  help="The workflow ID of the build to start"
)
parser.add_argument(
  "--gitTag", 
  help="The Git tag to consider"
)
args = parser.parse_args()

url = 'https://api.bitrise.io/v0.1/apps/' + args.appSlug + '/builds'
headers = 'Authorization: ' + args.token
data = {
  "hook_info": {
    "type": "bitrise",
  },
  "build_params": {
    "tag": args.gitTag,
    "workflow_id": args.workflowId
  },
  "environments": [
    {
      "mapped_to": "GIT_TAG",
      "value": args.gitTag
    }
  ]
}

response = requests.post(
  url,
  data = data,
  headers = headers
)

assert response.status_code == 200, "Bitrise API returns a not OK status"
