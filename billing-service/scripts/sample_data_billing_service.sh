#!/bin/bash

 url="http://localhost:8097"

candidates=(
  '{"firstName":"Mitt","lastName":"Romney","politicalParty":"Republican Party","election":"2012 Presidential Election","homeState":"Massachusetts","politcalExperience":"70th Governor of Massachusetts"}'
  '{"firstName":"Rocky","lastName":"Anderson","politicalParty":"Justice Party","election":"2012 Presidential Election","homeState":"Utah","politcalExperience":"33rd Mayor of Salt Lake City, Utah"}'
  '{"firstName":"Jill","lastName":"Stein","politicalParty":"Green Party","election":"2012 Presidential Election","homeState":"Massachusetts","politcalExperience":"None"}'
  '{"firstName":"Gary","lastName":"Johnson","politicalParty":"Libertarian Party","election":"2012 Presidential Election","homeState":"New Mexico","politcalExperience":"29th Governor of New Mexico"}'
  '{"firstName":"Virgil","lastName":"Goode","politicalParty":"Constitution Party","election":"2012 Presidential Election","homeState":"Virginia","politcalExperience":"U.S. House of Representatives from Virginia, 5th district"}'
  '{"firstName":"Barack","lastName":"Obama","politicalParty":"Democratic Party","election":"2012 Presidential Election","homeState":"Illinois","politcalExperience":"Illinois State Senator"}'
  '{"firstName":"Donald","lastName":"Trump","politicalParty":"Republican Party","election":"2016 Presidential Election","homeState":"New York","politcalExperience":"None"}'
  '{"firstName":"Chris","lastName":"Keniston","politicalParty":"Veterans Party","election":"2016 Presidential Election","homeState":"Texas","politcalExperience":"Air Force Veteran"}'
  '{"firstName":"Jill","lastName":"Stein","politicalParty":"Green Party","election":"2016 Presidential Election","homeState":"Massachusetts","politcalExperience":"None"}'
  '{"firstName":"Gary","lastName":"Johnson","politicalParty":"Libertarian Party","election":"2016 Presidential Election","homeState":"New Mexico","politcalExperience":"29th Governor of New Mexico"}'
  '{"firstName":"Darrell","lastName":"Castle","politicalParty":"Constitution Party","election":"2016 Presidential Election","homeState":"Tennessee","politcalExperience":"Commissioned Officer, U.S. Marine Corps"}'
  '{"firstName":"Hillary","lastName":"Clinton","politicalParty":"Democratic Party","election":"2016 Presidential Election","homeState":"New York","politcalExperience":"U.S. Senator, New York and 67th US Secretary of State"}'
)

echo "POSTing ${url}/voter/drop/candidates"
curl --request POST --url ${url}/voter/drop/candidates

echo "Dropping all existing candidate documents from candidates DB..."
echo "POSTing ${url}/candidate/drop/candidates"
curl --request POST --url ${url}/candidate/drop/candidates

echo ""

for candidate in "${candidates[@]}"
do
  echo "POSTing ${candidate}"
  curl --request POST \
   --url ${url}/candidate/candidates \
   --header 'content-type: application/json' \
   --data "${candidate}"
done



echo "Drop database and table"
echo "POST ${url}/billing-services"

