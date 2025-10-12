import instance from "../custom-axios/axios";

const auctionRepository = {
    listAll: async () => {
        return await instance.get("/auctions");
    }
};

export default auctionRepository;