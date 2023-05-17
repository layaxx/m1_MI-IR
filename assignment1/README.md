# MI-IR Assignment 1

The excel file is [here](https://1drv.ms/x/s!AjDDZyHBol6wjtsMQdHw_uJLsD1McQ?e=9FWajs). (read-only link)

The elasticSearch requests are in the `requests` subfolder, and can be executed by running

```bash
curl localhost:9200/shakespeare/_search?pretty -H "Content-Type: application/json" -d @file_with_query.json
```

# Unit Test status:
Tasks 1-4 currently pass, for the task 7:
- [x] 7.1
- [x] 7.2
- [x] 7.3
- [x] 7.4
- [x] 7.5
- [ ] 7.6
