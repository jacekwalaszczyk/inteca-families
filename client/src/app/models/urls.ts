export class Urls {
    static readonly familiesUrl        = 'api/families';
    static readonly familiesContentUrl = 'api/families/content';
    static readonly fathersUrl         = 'api/fathers';
    static readonly childrenUrl        = 'api/children';
}

export class Paths {
    static readonly menu                = 'menu';
    static readonly familydetailId      = 'familydetail/:id';
    static readonly familydetailIdChild = 'familydetail/:id/child/:pesel';
    static readonly fatherdetailId      = 'fatherdetail/:id';
    static readonly childdetailPesel    = 'childdetail/:pesel';
    static readonly childrenId          = 'children/:id';
    static readonly newfamily           = 'newfamily';
    static readonly searchfamilies      = 'searchfamilies';
    static readonly families            = 'families';  
}
