package com.spitbay.manager;

import com.spitbay.dao.*;
import com.spitbay.service.*;

// Simple Singleton for managing all services
public class ServiceManager {
    private static ServiceManager instance;
    private final UserService userService;
    private final PGService pgService;
    private final BlogService blogService;
    private final ScoringService scoringService;
    
    private ServiceManager() {
        // Initialize DAOs
        SeniorDAO seniorDAO = new SeniorDAO();
        UIDDAO uidDAO = new UIDDAO();
        BlogDAO blogDAO = new BlogDAO();
        PGListingDAO pgListingDAO = new PGListingDAO();
        
        // Initialize services with DAOs
        this.userService = new UserService(seniorDAO, uidDAO);
        this.pgService = new PGService(pgListingDAO);
        this.blogService = new BlogService(blogDAO);
        this.scoringService = new ScoringService();
    }
    
    // Get singleton instance
    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return instance;
    }
    
    // Get service instances
    public UserService getUserService() {
        return userService;
    }
    
    public PGService getPGService() {
        return pgService;
    }
    
    public BlogService getBlogService() {
        return blogService;
    }
    
    public ScoringService getScoringService() {
        return scoringService;
    }
}
