/*
 * packed_bed_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:26 by COMSOL 6.3.0.290. */
public class packed_bed_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Porous_Catalysts");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("C3H6");
    model.component("comp1").physics("tds").field("concentration")
         .component(new String[]{"C3H6", "CO", "CO2", "H2O", "O2"});
    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");
    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("c").field("dimensionless").field("P");
    model.component("comp1").physics("c").field("dimensionless").component(new String[]{"P"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/c", true);

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 0.3, 1);
    model.component("comp1").geom("geom1").run("i1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("P_feed", "2[atm]", "\u8fdb\u6599\u538b\u529b");
    model.param().set("Tr", "508[K]", "\u53cd\u5e94\u5668\u6e29\u5ea6");
    model.param().set("D_CO", "0.0487[cm^2/s]", "CO \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("D_O2", "0.0469[cm^2/s]", "O2 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("D_C3H6", "0.0487[cm^2/s]", "C3H6 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("D_CO2", "0.0487[cm^2/s]", "CO2 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("D_H2O", "0.0487[cm^2/s]", "H2O \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("E1", "1.09e5[J/mol]", "R1 \u7684\u6d3b\u5316\u80fd");
    model.param().set("E2", "1.26e5[J/mol]", "R2 \u7684\u6d3b\u5316\u80fd");
    model.param().set("A1", "7.07e13[m^3/mol/s]", "\u53cd\u5e94 1 \u7684\u6307\u524d\u56e0\u5b50");
    model.param().set("A2", "1.47e15[m^3/mol/s]", "\u53cd\u5e94 2 \u7684\u6307\u524d\u56e0\u5b50");
    model.param().set("Aco", "8.1[m^3/mol]", "\u5438\u9644\u9879\u5e38\u6570");
    model.param().set("Ac3h6", "257.9[m^3/mol]", "\u5438\u9644\u9879\u5e38\u6570");
    model.param().set("Eco", "-3400[J/mol]", "\u5438\u9644\u9879\u7684\u6d3b\u5316\u80fd");
    model.param().set("Ec3h6", "1588[J/mol]", "\u5438\u9644\u9879\u7684\u6d3b\u5316\u80fd");
    model.param().set("u_feed", "75[cm/s]", "\u7ebf\u6027\u6d41\u5165\u901f\u5ea6");
    model.param()
         .set("por_b", "1-rho_b/rho_p", "\u586b\u5145\u5e8a\u7684\u5b54\u9699\u7387\uff08\u7a7a\u9699\u4f53\u79ef/\u603b\u4f53\u79ef\uff09");
    model.param().set("por_pel", "0.90", "\u9897\u7c92\u50ac\u5316\u5242\u7684\u5b54\u9699\u7387");
    model.param().set("rho_b", "0.51[g/cm^3]", "\u586b\u5145\u5e8a\u5bc6\u5ea6");
    model.param().set("rho_p", "0.68[g/cm^3]", "\u9897\u7c92\u5bc6\u5ea6");
    model.param().set("Rr", "5[cm]", "\u53cd\u5e94\u5668\u534a\u5f84");
    model.param().set("Ra", "pi*Rr^2", "\u53cd\u5e94\u5668\u9762\u79ef");
    model.param().set("mu", "0.028e-2[g/cm/s]", "\u6d41\u4f53\u9ecf\u5ea6");
    model.param().set("rho_feed", "1.292[kg/m^3]", "\u5165\u53e3\u6c14\u4f53\u5bc6\u5ea6");
    model.param().set("Ctot_feed", "P_feed/R_const/Tr", "\u603b\u8fdb\u6599\u6d53\u5ea6");
    model.param().set("x_CO_feed", "0.005", "\u8fdb\u6599\u4e2d\u7684 CO \u6469\u5c14\u5206\u6570");
    model.param().set("x_O2_feed", "0.0075", "\u8fdb\u6599\u4e2d\u7684 O2 \u6469\u5c14\u5206\u6570");
    model.param().set("x_C3H6_feed", "0.000125", "\u8fdb\u6599\u4e2d\u7684 C3H6 \u6469\u5c14\u5206\u6570");
    model.param()
         .set("Cinert_feed", "(1-x_CO_feed-x_O2_feed-x_C3H6_feed)*Ctot_feed", "\u8fdb\u6599\u4e2d\u7684\u60f0\u6027\u6c14\u4f53 (N2) \u6d53\u5ea6");
    model.param().set("Ap", "3/rp", "\u5355\u4f4d\u4f53\u79ef\u7684\u9897\u7c92\u8868\u9762\u79ef");
    model.param().set("rp", "0.25[cm]", "\u9897\u7c92\u534a\u5f84");

    model.variable().create("var1");

//    To import content from file, use:
//    model.variable("var1").loadFile("FILENAME");
    model.variable("var1")
         .set("Ctot", "(Cinert_feed+comp1.CO+comp1.O2+comp1.CO2+comp1.C3H6+comp1.H2O)", "\u603b\u6d53\u5ea6");
    model.variable("var1").set("KCO", "Aco*exp(-Eco/R_const/Tr)", "CO \u5438\u9644\u5e38\u6570");
    model.variable("var1").set("KC3H6", "Ac3h6*exp(-Ec3h6/R_const/Tr)", "C3H6 \u5438\u9644\u5e38\u6570");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tds").feature("cdm1").set("u", new String[]{"u", "0", "0"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_C3H6", new String[]{"D_C3H6", "0", "0", "0", "D_C3H6", "0", "0", "0", "D_C3H6"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_CO", new String[]{"D_CO", "0", "0", "0", "D_CO", "0", "0", "0", "D_CO"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_CO2", new String[]{"D_CO2", "0", "0", "0", "D_CO2", "0", "0", "0", "D_CO2"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_H2O", new String[]{"D_H2O", "0", "0", "0", "D_H2O", "0", "0", "0", "D_H2O"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_O2", new String[]{"D_O2", "0", "0", "0", "D_O2", "0", "0", "0", "D_O2"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "1e-6", 0);
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "1e-6", 1);
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "1e-6", 2);
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "1e-6", 3);
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "1e-6", 4);
    model.component("comp1").physics("tds").create("reac1", "Reactions", 1);
    model.component("comp1").physics("tds").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_C3H6", "-Ap*C3H6flux*(1-por_b)", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_CO", "-Ap*COflux*(1-por_b)", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_CO2", "-Ap*CO2flux*(1-por_b)", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_H2O", "-Ap*H2Oflux*(1-por_b)", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_O2", "-Ap*O2flux*(1-por_b)", 0);
    model.component("comp1").physics("tds").create("in1", "Inflow", 0);
    model.component("comp1").physics("tds").feature("in1").selection().set(1);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "x_C3H6_feed*Ctot_feed", 0);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "x_CO_feed*Ctot_feed", 1);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "x_O2_feed*Ctot_feed", 4);
    model.component("comp1").physics("tds").feature("in1").set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("tds").create("out1", "Outflow", 0);
    model.component("comp1").physics("tds").feature("out1").selection().set(2);
    model.component("comp1").physics("c")
         .label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b\uff1aErgun \u65b9\u7a0b");
    model.component("comp1").physics("c").prop("Units").set("DependentVariableQuantity", "pressure");
    model.component("comp1").physics("c").prop("Units").setIndex("CustomSourceTermUnit", "N/m^3", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1")
         .setIndex("f", "Px+(150*mu*u/(2*Rr)^2*(1-por_b)^2/por_b^3+1.75*rho_feed*u^2/(2*Rr)*(1-por_b)/por_b^3)", 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("da", 0, 0);
    model.component("comp1").physics("c").feature("init1").set("P", "P_feed");
    model.component("comp1").physics("c").create("dir1", "DirichletBoundary", 0);
    model.component("comp1").physics("c").feature("dir1").selection().set(1);
    model.component("comp1").physics("c").feature("dir1").setIndex("r", "P_feed", 0);

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").physics().create("chem", "Chemistry", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics().create("tds2", "DilutedSpecies", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/tds2", true);

    model.component("comp2").physics("tds2").field("concentration").field("C3H6p");
    model.component("comp2").physics("tds2").field("concentration")
         .component(new String[]{"C3H6p", "COp", "CO2p", "H2Op", "O2p"});

    model.component("comp2").geom("geom2").create("sq1", "Square");
    model.component("comp2").geom("geom2").run("sq1");
    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("chem").create("rch1", "ReactionChem", 2);
    model.component("comp2").physics("chem").feature("rch1").set("formula", "CO+1/2O2=>CO2");
    model.component("comp2").physics("chem").feature("rch1")
         .set("r", "chem.kf_1*chem.c_CO*chem.c_O2/(1+KCO*chem.c_CO+KC3H6*chem.c_C3H6)^2");
    model.component("comp2").physics("chem").feature("rch1").set("bulkFwdOrder", 2);
    model.component("comp2").physics("chem").feature("rch1").set("useArrhenius", true);
    model.component("comp2").physics("chem").feature("rch1").set("Af", "A1");
    model.component("comp2").physics("chem").feature("rch1").set("Ef", "E1");
    model.component("comp2").physics("chem").create("rch2", "ReactionChem", 2);
    model.component("comp2").physics("chem").feature("rch2").set("formula", "C3H6+9/2O2=>3H2O+3CO2");
    model.component("comp2").physics("chem").feature("rch2").set("bulkFwdOrder", 2);
    model.component("comp2").physics("chem").feature("rch2")
         .set("r", "chem.kf_2*chem.c_C3H6*chem.c_O2/(1+KCO*chem.c_CO+KC3H6*chem.c_C3H6)^2");
    model.component("comp2").physics("chem").feature("rch2").set("useArrhenius", true);
    model.component("comp2").physics("chem").feature("rch2").set("Af", "A2");
    model.component("comp2").physics("chem").feature("rch2").set("Ef", "E2");
    model.component("comp2").physics("chem").create("spec1", "SpeciesChem", 2);
    model.component("comp2").physics("chem").feature("spec1").set("specName", "N2");
    model.component("comp2").physics("chem").feature("N2").set("sType", "solvent");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T", "Tr");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p", "P_feed");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds2");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "C3H6p", 0, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "COp", 1, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "CO2p", 2, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "H2Op", 3, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "Cinert_feed", 4, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "O2p", 5, 0);
    model.component("comp2").physics("tds2").prop("TransportMechanism").set("Convection", false);
    model.component("comp2").physics("tds2").feature("cdm1")
         .set("D_C3H6p", new String[]{"0", "0", "0", "0", "(D_C3H6/rp^2)*y^2", "0", "0", "0", "0"});
    model.component("comp2").physics("tds2").feature("cdm1")
         .set("D_COp", new String[]{"0", "0", "0", "0", "(D_CO/rp^2)*y^2", "0", "0", "0", "0"});
    model.component("comp2").physics("tds2").feature("cdm1")
         .set("D_CO2p", new String[]{"0", "0", "0", "0", "(D_CO2/rp^2)*y^2", "0", "0", "0", "0"});
    model.component("comp2").physics("tds2").feature("cdm1")
         .set("D_H2Op", new String[]{"0", "0", "0", "0", "(D_H2O/rp^2)*y^2", "0", "0", "0", "0"});
    model.component("comp2").physics("tds2").feature("cdm1")
         .set("D_O2p", new String[]{"0", "0", "0", "0", "(D_O2/rp^2)*y^2", "0", "0", "0", "0"});
    model.component("comp2").physics("tds2").feature("init1").setIndex("initc", "1e-6", 0);
    model.component("comp2").physics("tds2").feature("init1").setIndex("initc", "1e-6", 1);
    model.component("comp2").physics("tds2").feature("init1").setIndex("initc", "1e-6", 2);
    model.component("comp2").physics("tds2").feature("init1").setIndex("initc", "1e-6", 3);
    model.component("comp2").physics("tds2").feature("init1").setIndex("initc", "1e-6", 4);
    model.component("comp2").physics("tds2").create("reac1", "Reactions", 2);
    model.component("comp2").physics("tds2").feature("reac1").selection().all();
    model.component("comp2").physics("tds2").feature("reac1").setIndex("R_C3H6p", "y^2/1[m^2]*chem.R_C3H6", 0);
    model.component("comp2").physics("tds2").feature("reac1").setIndex("R_COp", "y^2/1[m^2]*chem.R_CO", 0);
    model.component("comp2").physics("tds2").feature("reac1").setIndex("R_CO2p", "y^2/1[m^2]*chem.R_CO2", 0);
    model.component("comp2").physics("tds2").feature("reac1").setIndex("R_H2Op", "y^2/1[m^2]*chem.R_H2O", 0);
    model.component("comp2").physics("tds2").feature("reac1").setIndex("R_O2p", "y^2/1[m^2]*chem.R_O2", 0);
    model.component("comp2").physics("tds2").create("conc1", "Concentration", 1);
    model.component("comp2").physics("tds2").feature("conc1").selection().set(3);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("species", true, 0);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("c0", "C3H6bulk", 0);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("species", true, 1);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("c0", "CObulk", 1);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("species", true, 2);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("c0", "CO2bulk", 2);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("species", true, 3);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("c0", "H2Obulk", 3);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("species", true, 4);
    model.component("comp2").physics("tds2").feature("conc1").setIndex("c0", "O2bulk", 4);
    model.component("comp2").physics("tds2").feature("conc1").set("constraintType", "unidirectionalConstraint");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().all();
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"x*0.30"});
    model.component("comp1").cpl("genext1").set("usesrcmap", true);
    model.component("comp2").cpl().create("genext2", "GeneralExtrusion");
    model.component("comp2").cpl("genext2").selection().geom("geom2", 1);
    model.component("comp2").cpl("genext2").selection().set(3);
    model.component("comp2").cpl("genext2").set("dstmap", new String[]{"x/0.30", ""});
    model.component("comp2").cpl("genext2").set("usesrcmap", true);
    model.component("comp2").cpl("genext2").set("srcmap", new String[]{"x", ""});

    model.component("comp1").variable().create("var2");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("C3H6flux", "comp2.genext2(D_C3H6*comp2.C3H6py*1[m]/rp)", "C3H6 \u8fb9\u754c\u901a\u91cf");
    model.component("comp1").variable("var2")
         .set("COflux", "comp2.genext2(D_CO*comp2.COpy*1[m]/rp)", "CO \u8fb9\u754c\u901a\u91cf");
    model.component("comp1").variable("var2")
         .set("CO2flux", "comp2.genext2(D_CO2*comp2.CO2py*1[m]/rp)", "CO2 \u8fb9\u754c\u901a\u91cf");
    model.component("comp1").variable("var2")
         .set("H2Oflux", "comp2.genext2(D_H2O*comp2.H2Opy*1[m]/rp)", "H2O \u8fb9\u754c\u901a\u91cf");
    model.component("comp1").variable("var2")
         .set("O2flux", "comp2.genext2(D_O2*comp2.O2py*1[m]/rp)", "O2 \u8fb9\u754c\u901a\u91cf");
    model.component("comp1").variable("var2").set("u", "u_feed*(P_feed/comp1.P)", "\u7ebf\u6027\u901f\u5ea6");
    model.component("comp2").variable().create("var3");
    model.component("comp2").variable("var3").selection().geom("geom2", 1);
    model.component("comp2").variable("var3").selection().set(3);

//    To import content from file, use:
//    model.component("comp2").variable("var3").loadFile("FILENAME");
    model.component("comp2").variable("var3")
         .set("C3H6bulk", "comp1.genext1(comp1.C3H6)*por_pel", "C3H6 \u672c\u4f53\u6d53\u5ea6");
    model.component("comp2").variable("var3")
         .set("CObulk", "comp1.genext1(comp1.CO)*por_pel", "CO \u672c\u4f53\u6d53\u5ea6");
    model.component("comp2").variable("var3")
         .set("CO2bulk", "comp1.genext1(comp1.CO2)*por_pel", "CO2 \u672c\u4f53\u6d53\u5ea6");
    model.component("comp2").variable("var3")
         .set("H2Obulk", "comp1.genext1(comp1.H2O)*por_pel", "H2O \u672c\u4f53\u6d53\u5ea6");
    model.component("comp2").variable("var3")
         .set("O2bulk", "comp1.genext1(comp1.O2)*por_pel", "O2 \u672c\u4f53\u6d53\u5ea6");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.0025);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().set(1, 4);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("elemcount", 30);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("elemratio", 0.2);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis2", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").selection().set(3);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").set("numelem", 120);
    model.component("comp2").mesh("mesh2").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg1").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tds)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"C3H6"});
    model.result("pg1").feature("lngr1").label("\u7269\u8d28 C3H6");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("autosolution", false);
    model.result("pg1").feature("lngr1").set("autoexpr", false);
    model.result("pg1").feature("lngr1").set("autodescr", false);
    model.result("pg1").feature("lngr1").set("legendprefix", "C3H6 ");
    model.result("pg1").feature("lngr1").set("descractive", true);
    model.result("pg1").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "x");
    model.result("pg1").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr2").selection().set(1);
    model.result("pg1").feature("lngr2").set("expr", new String[]{"CO"});
    model.result("pg1").feature("lngr2").label("\u7269\u8d28 CO");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("autosolution", false);
    model.result("pg1").feature("lngr2").set("autoexpr", false);
    model.result("pg1").feature("lngr2").set("autodescr", false);
    model.result("pg1").feature("lngr2").set("legendprefix", "CO ");
    model.result("pg1").feature("lngr2").set("descractive", true);
    model.result("pg1").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg1").create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").set("xdata", "expr");
    model.result("pg1").feature("lngr3").set("xdataexpr", "x");
    model.result("pg1").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr3").selection().set(1);
    model.result("pg1").feature("lngr3").set("expr", new String[]{"CO2"});
    model.result("pg1").feature("lngr3").label("\u7269\u8d28 CO2");
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("autosolution", false);
    model.result("pg1").feature("lngr3").set("autoexpr", false);
    model.result("pg1").feature("lngr3").set("autodescr", false);
    model.result("pg1").feature("lngr3").set("legendprefix", "CO2 ");
    model.result("pg1").feature("lngr3").set("descractive", true);
    model.result("pg1").feature("lngr3").set("descr", "\u6d53\u5ea6");
    model.result("pg1").create("lngr4", "LineGraph");
    model.result("pg1").feature("lngr4").set("xdata", "expr");
    model.result("pg1").feature("lngr4").set("xdataexpr", "x");
    model.result("pg1").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr4").selection().set(1);
    model.result("pg1").feature("lngr4").set("expr", new String[]{"H2O"});
    model.result("pg1").feature("lngr4").label("\u7269\u8d28 H2O");
    model.result("pg1").feature("lngr4").set("legend", true);
    model.result("pg1").feature("lngr4").set("autosolution", false);
    model.result("pg1").feature("lngr4").set("autoexpr", false);
    model.result("pg1").feature("lngr4").set("autodescr", false);
    model.result("pg1").feature("lngr4").set("legendprefix", "H2O ");
    model.result("pg1").feature("lngr4").set("descractive", true);
    model.result("pg1").feature("lngr4").set("descr", "\u6d53\u5ea6");
    model.result("pg1").create("lngr5", "LineGraph");
    model.result("pg1").feature("lngr5").set("xdata", "expr");
    model.result("pg1").feature("lngr5").set("xdataexpr", "x");
    model.result("pg1").feature("lngr5").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr5").selection().set(1);
    model.result("pg1").feature("lngr5").set("expr", new String[]{"O2"});
    model.result("pg1").feature("lngr5").label("\u7269\u8d28 O2");
    model.result("pg1").feature("lngr5").set("legend", true);
    model.result("pg1").feature("lngr5").set("autosolution", false);
    model.result("pg1").feature("lngr5").set("autoexpr", false);
    model.result("pg1").feature("lngr5").set("autodescr", false);
    model.result("pg1").feature("lngr5").set("legendprefix", "O2 ");
    model.result("pg1").feature("lngr5").set("descractive", true);
    model.result("pg1").feature("lngr5").set("descr", "\u6d53\u5ea6");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u6d53\u5ea6, C3H6 (tds)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"C3H6"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6d53\u5ea6, CO (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"CO"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6d53\u5ea6, CO2 (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"CO2"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u6d53\u5ea6, H2O (tds)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"H2O"});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").label("\u6d53\u5ea6, O2 (tds)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"O2"});
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1);
    model.result("pg7").feature("lngr1").set("expr", "P");
    model.result("pg7").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b\uff1aErgun \u65b9\u7a0b");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").label("\u6d53\u5ea6, C3H6p (tds2)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"C3H6p"});
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result("pg8").set("typeintitle", true);
    model.result("pg8").create("arws1", "ArrowSurface");
    model.result("pg8").feature("arws1").set("expr", new String[]{"tds2.tflux_C3H6px", "tds2.tflux_C3H6py"});
    model.result("pg8").feature("arws1").set("xnumber", 10);
    model.result("pg8").feature("arws1").set("ynumber", 10);
    model.result("pg8").feature("arws1").set("color", "black");
    model.result("pg8").feature("arws1").create("sel1", "Selection");
    model.result("pg8").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").label("\u6d53\u5ea6, COp (tds2)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"COp"});
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").set("typeintitle", true);
    model.result("pg9").create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").set("expr", new String[]{"tds2.tflux_COpx", "tds2.tflux_COpy"});
    model.result("pg9").feature("arws1").set("xnumber", 10);
    model.result("pg9").feature("arws1").set("ynumber", 10);
    model.result("pg9").feature("arws1").set("color", "black");
    model.result("pg9").feature("arws1").create("sel1", "Selection");
    model.result("pg9").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").label("\u6d53\u5ea6, CO2p (tds2)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").set("expressionintitle", false);
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"CO2p"});
    model.result("pg10").feature("surf1").set("colortable", "Prism");
    model.result("pg10").set("typeintitle", true);
    model.result("pg10").create("arws1", "ArrowSurface");
    model.result("pg10").feature("arws1").set("expr", new String[]{"tds2.tflux_CO2px", "tds2.tflux_CO2py"});
    model.result("pg10").feature("arws1").set("xnumber", 10);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg10").feature("arws1").set("ynumber", 10);
    model.result("pg10").feature("arws1").set("color", "black");
    model.result("pg10").feature("arws1").create("sel1", "Selection");
    model.result("pg10").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").label("\u6d53\u5ea6, H2Op (tds2)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"H2Op"});
    model.result("pg11").feature("surf1").set("colortable", "Prism");
    model.result("pg11").set("typeintitle", true);
    model.result("pg11").create("arws1", "ArrowSurface");
    model.result("pg11").feature("arws1").set("expr", new String[]{"tds2.tflux_H2Opx", "tds2.tflux_H2Opy"});
    model.result("pg11").feature("arws1").set("xnumber", 10);
    model.result("pg11").feature("arws1").set("ynumber", 10);
    model.result("pg11").feature("arws1").set("color", "black");
    model.result("pg11").feature("arws1").create("sel1", "Selection");
    model.result("pg11").feature("arws1").feature("sel1").selection().set(1);
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").label("\u6d53\u5ea6, O2p (tds2)");
    model.result("pg12").set("titletype", "custom");
    model.result("pg12").set("prefixintitle", "");
    model.result("pg12").set("expressionintitle", false);
    model.result("pg12").set("typeintitle", false);
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", new String[]{"O2p"});
    model.result("pg12").feature("surf1").set("colortable", "Prism");
    model.result("pg12").set("typeintitle", true);
    model.result("pg12").create("arws1", "ArrowSurface");
    model.result("pg12").feature("arws1").set("expr", new String[]{"tds2.tflux_O2px", "tds2.tflux_O2py"});
    model.result("pg12").feature("arws1").set("xnumber", 10);
    model.result("pg12").feature("arws1").set("ynumber", 10);
    model.result("pg12").feature("arws1").set("color", "black");
    model.result("pg12").feature("arws1").create("sel1", "Selection");
    model.result("pg12").feature("arws1").feature("sel1").selection().set(1);
    model.result("pg1").run();
    model.result("pg1").set("title", "\u6cbf\u53cd\u5e94\u5668\u957f\u5ea6\u65b9\u5411\u7684\u6d53\u5ea6");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u53cd\u5e94\u5668\u957f\u5ea6 (m)");
    model.result("pg1").run();
    model.result("pg9").run();
    model.result("pg9").feature("surf1").create("hght1", "Height");
    model.result("pg9").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "P_feed", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "P_feed", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "Pa", 0);
    model.study("std1").feature("stat").setIndex("pname", "rp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0.0025 0.0018", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "5/30", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "5/30", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 1, 1, 1);
    model.result().dataset("cln1").set("genparaactive", true);
    model.result().dataset("cln1").set("genparadist", "-10/30 -20/30");
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u9897\u7c92\u5927\u5c0f\u6bd4\u8f83");
    model.result("pg13").create("lngr1", "LineGraph");
    model.result("pg13").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg13").feature("lngr1").set("linewidth", "preference");
    model.result("pg13").feature("lngr1").label("\u5bf9\u4e8e rp=2.5 mm");
    model.result("pg13").feature("lngr1").set("data", "cln1");
    model.result("pg13").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg13").feature("lngr1").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg13").feature("lngr1").set("expr", "C3H6p");
    model.result("pg13").feature("lngr1").set("linewidth", 2);
    model.result("pg13").feature("lngr1").set("legend", true);
    model.result("pg13").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg13").feature("lngr1").set("legendpattern", "eval(x*0.3,cm) cm, rp=eval(rp,mm) mm");
    model.result("pg13").feature().duplicate("lngr2", "lngr1");
    model.result("pg13").run();
    model.result("pg13").feature("lngr2").label("\u5bf9\u4e8e rp=1.8 mm");
    model.result("pg13").feature("lngr2").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg13").feature("lngr2").set("linemarker", "triangle");
    model.result("pg13").feature("lngr2").set("markerpos", "interp");
    model.result("pg13").run();
    model.result("pg13").set("legendpos", "upperleft");
    model.result("pg13").set("titletype", "manual");
    model.result("pg13").set("title", "\u6d53\u5ea6\uff0cC3H6p (mol/m<sup>3</sup>)");
    model.result("pg13").run();
    model.result("pg7").run();
    model.result("pg7").label("\u538b\u964d");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg7").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "first", 0);
    model.result("pg1").run();

    model.title("\u586b\u5145\u5e8a\u53cd\u5e94\u5668");

    model
         .description("\u4e00\u79cd\u7814\u7a76\u542b\u50ac\u5316\u5242\u7684\u4e00\u7ef4\u586b\u5145\u5e8a\u53cd\u5e94\u5668\u6a21\u578b\u4e2d\u8026\u5408\u5b8f\u89c2\u7269\u8d28\u548c\u5fae\u89c2\u7269\u8d28\u5e73\u8861\u7684\u65b9\u6cd5\u662f\u4f7f\u7528\u591a\u4e2a\u51e0\u4f55\u3002\u8be5\u6a21\u578b\u9700\u8981\u4e24\u4e2a\u51e0\u4f55\uff0c\u5206\u522b\u7528\u4e8e\u5b8f\u89c2\u548c\u5fae\u89c2\u7cfb\u7edf\uff0c\u8ba1\u7b97\u6cbf\u586b\u5145\u5e8a\u53cd\u5e94\u5668\u4ee5\u53ca\u50ac\u5316\u9897\u7c92\u5185\u7684\u53cd\u5e94\u7269\u8d28\u6d53\u5ea6\u5206\u5e03\u3002");

    model.label("packed_bed_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
