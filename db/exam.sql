select cmp.name as empl, prs.name as company from company cmp join person prs on cmp.id=prs.company_id where cmp.id!=5;

select cmp.id as comp, count(prs.id) as qty from company cmp join person prs on cmp.id=prs.company_id group by cmp.id
having count(prs.id) in
(select max(tmp.qty) as max_qty from
(select cmp.id as comp, count(prs.id) as qty
from company cmp join person prs on cmp.id=prs.company_id group by cmp.id) as tmp);