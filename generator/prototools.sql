create schema TOOLS

select new Map(pp.id as prodParamId, pp.product.id as prodId, pp.parameter.id as parameterId, pp.optional as optional, ppn.name as parameterName)
from ProductParameter pp join pp.parameter.parameterNames ppn
where pp.product.id = :product_id and ppn.language.id=:language_id

update product_parameter pp set pp.optional=true where pp.id = 2 

SELECT
  SALESORDER0_.ID AS COL_0_0_ 
 FROM
  SALESORDER SALESORDER0_ 
