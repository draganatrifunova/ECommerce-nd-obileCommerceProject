import instance from "../custom-axios/axios";

const auctionRepository = {
    listAll: async () => {
        return await instance.get("/auctions");
    },

    findById: async (id) => {
        return await instance.get(`/auctions/${id}`);
    },
    cancelByIdAndOrganizer: async (id) => {
        return await instance.put(`/auctions/${id}/cancel`);
    },
    joinAuction: async (id) => {
        return await instance.post(`/auctions/${id}/addVisitor`);
    },
    startAuction: async (id) => {
        return await instance.put(`/auctions/${id}/start`)
    },
    finishAuction: async (id) => {
        return await instance.put(`/auctions/${id}/finish`)
    },
    addOffer: async (id, data) => {
        return await instance.post(`/auctions/${id}/addOffer`, data);
    },
    getLastOffer: async (id) => {
        return await instance.get(`/auctions/${id}/lastOffer`);
    }
};

export default auctionRepository;