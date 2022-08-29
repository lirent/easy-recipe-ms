--Recipe
INSERT INTO RECIPE (id, author, category, servings, title, ts_version) VALUES (1, 'Rossi', 0, 8, 'Big night pizza', CURRENT_TIMESTAMP());
INSERT INTO RECIPE (id, author, category, servings, title, ts_version) VALUES (2, 'Marco', 1, 4, 'Crock Pot Roast', CURRENT_TIMESTAMP());
INSERT INTO RECIPE (id, author, category, servings, title, ts_version) VALUES (3, 'Lorenzo', 3, 4, 'Curried Lentils and Rice', CURRENT_TIMESTAMP());

-- Ingredients (ID, NAME, QUANTITY, UNIT, RECIPE_ID)
INSERT INTO INGREDIENT VALUES (1, 'yeast', 2, 'teaspoons', 1);
INSERT INTO INGREDIENT VALUES (2, 'flour', 5, 'cups', 1);
INSERT INTO INGREDIENT VALUES (3, 'vegetable oil', 4, 'tablespoons', 1);
INSERT INTO INGREDIENT VALUES (4, 'hot water', 2, 'cups', 1);
INSERT INTO INGREDIENT VALUES (5, 'pizza sauce', 1, 'cup', 1);
INSERT INTO INGREDIENT VALUES (6, 'mozzarella cheese', 300, 'gr', 1);
INSERT INTO INGREDIENT VALUES (7, 'salt', 1, 'teaspoon', 1);

INSERT INTO INGREDIENT VALUES (8, 'beef roast', 500, 'gr', 2);
INSERT INTO INGREDIENT VALUES (9, 'brown gravy mix', 1, 'package', 2);
INSERT INTO INGREDIENT VALUES (10, 'dried Italian salad dressing mix', 1, 'package', 2);
INSERT INTO INGREDIENT VALUES (11, 'dry ranch dressing mix', 1, 'package', 2);
INSERT INTO INGREDIENT VALUES (12, 'water', 1, 'cup', 2);

INSERT INTO INGREDIENT VALUES (13, 'vegetable broth', 3, 'cups', 3);
INSERT INTO INGREDIENT VALUES (14, 'dried green lentils', 1, 'cup', 3);
INSERT INTO INGREDIENT VALUES (15, 'basmati rice', 1, 'cup', 3);
INSERT INTO INGREDIENT VALUES (16, 'curry powder', 1, 'tbs', 3);
INSERT INTO INGREDIENT VALUES (17, 'salt', 1, 'tbs', 3);

--Instructions
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (1, 1, 'Add hot water to yeast in a large bowl and let sit for 15 minutes.', 1);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (2, 2, 'Mix in oil, sugar, salt, and flour and let sit for 1 hour.', 1);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (3, 3, 'Knead the dough and spread onto a pan.', 1);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (4, 4, 'Spread pizza sauce and sprinkle cheese.', 1);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (5, 5, 'Add any optional toppings as you wish.', 1);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (6, 6, 'Bake at 400 deg Fahrenheit for 15 minutes.', 1);

INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (7, 1, 'Place beef roast in crock pot.', 2);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (8, 2, 'Mix the dried mixes together in a bowl and sprinkle over the roast.', 2);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (9, 3, 'Pour the water around the roast.', 2);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (10, 4, 'Place beef roast in crock pot.', 2);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (11, 5, 'Cook on low for 7-9 hours.', 2);

INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (12, 1, 'Bring broth to a low boil.', 3);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (13, 2, 'Add curry powder and salt.', 3);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (14, 3, 'Cook lentils for 20 minutes.', 3);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (15, 4, 'Add rice and simmer for 20 minutes.', 3);
INSERT INTO INSTRUCTION (id, step, text, recipe_id) VALUES (16, 5, 'Enjoy!', 3);





