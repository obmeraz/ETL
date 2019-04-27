
/**********************************************************/
/*                         Run packages                   */
/**********************************************************/
--SRC
call pkg_src_load.pr_load_src_customers();
call pkg_src_load.pr_load_src_employees();
call pkg_src_load.pr_load_src_fkt_table();
call pkg_src_load.pr_load_src_locations();
call pkg_src_load.pr_load_src_payment_types();
call pkg_src_load.pr_load_src_products();
call pkg_src_load.pr_load_src_stores();

--3NF
--Locations
call pkg_3nf_locations.pr_start_all_procedurs();
--Stores
call pkg_3nf_stores.pr_start_all_procedurs();
--Customers
call pkg_3nf_customers.pr_start_all_procedurs();
--Employees
call pkg_3nf_employees.pr_load_wrk_positions();
call pkg_3nf_employees.pr_load_wrk_employees();
call pkg_3nf_employees.pr_load_ce_positions();
call pkg_3nf_employees.pr_load_ce_employees();
--Payment Types
call pkg_3nf_payment_types.pr_load_wrk_payment_types();
call pkg_3nf_payment_types.pr_load_ce_payment_types();
--Products
call pkg_3nf_products.pr_load_wrk_categories();
call pkg_3nf_products.pr_load_wrk_subcategories();
call pkg_3nf_products.pr_load_wrk_products();
call pkg_3nf_products.pr_load_ce_categories();
call pkg_3nf_products.pr_load_ce_subcategories();
call pkg_3nf_products.pr_load_ce_products();
--Stores
call pkg_3nf_stores.pr_start_all_procedurs();
--Sales
call pkg_3nf_sales.pr_load_wrk_sales();
call pkg_3nf_sales.pr_load_ce_sales();

--DM
--Locations
call pkg_dm_locations.pr_load_wrk_dm_locations();
call pkg_dm_locations.pr_load_dim_locations();
--Stores
call pkg_dm_stores.pr_load_wrk_dm_stores();
call pkg_dm_stores.pr_load_dim_stores();
--Customers
call pkg_dm_customers.pr_load_wrk_dm_customers();
call pkg_dm_customers.pr_load_dim_customers();
--Employees
call pkg_dm_employees.pr_load_wrk_dm_employees();
call pkg_dm_employees.pr_load_dim_employees();
--Payment Types
call pkg_dm_payment_types.pr_load_wrk_dm_payment_types();
call pkg_dm_payment_types.pr_load_dim_payment_types();
--Products
call pkg_dm_products.pr_load_wrk_dm_products();
call pkg_dm_products.pr_load_dim_products();
--Stores
call pkg_dm_stores.pr_load_wrk_dm_stores();
call pkg_dm_stores.pr_load_dim_stores();
--Sales
call pkg_dm_sales.pr_load_wrk_dm_sales();
call pkg_dm_sales.pr_load_fct_sales();