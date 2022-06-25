rs.initiate({
  _id: "shard01",
  version: 1,
  members: [
    { _id: 0, host: "shard01-a:27017" },
    { _id: 1, host: "shard01-b:27017" }
  ],
});