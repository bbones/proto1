create schema TOOLS;
CREATE SCHEMA VALUEPROVIDER;


select new Map(pp.id as prodParamId, pp.product.id as prodId, pp.parameter.id as parameterId, pp.optional as optional, ppn.name as parameterName)
from ProductParameter pp join pp.parameter.parameterNames ppn
where pp.product.id = :product_id and ppn.language.id=:language_id

update product_parameter pp set pp.optional=true where pp.id = 2 

select so.id as soId, csides.party.id as partyId, csides.party.address as address, pn.lastName, en.name
from SalesOrder so 
	join so.contractSupplement cs 
	join cs.contract c 
	join c.contractSides csides 
	join csides.party p 
	left join p.personNames pn
	left join p.enterpriseNames en
where pn.language.id = 1