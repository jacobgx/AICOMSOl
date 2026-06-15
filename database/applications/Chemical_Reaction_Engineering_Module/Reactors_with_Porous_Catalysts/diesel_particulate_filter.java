/*
 * diesel_particulate_filter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:26 by COMSOL 6.3.0.290. */
public class diesel_particulate_filter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Porous_Catalysts");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("c1_O2");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"c1_O2"});
    model.component("comp1").physics().create("tds2", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds2").field("concentration").field("c2_C");
    model.component("comp1").physics("tds2").field("concentration").component(new String[]{"c2_C"});
    model.component("comp1").physics().create("tds3", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds3").field("concentration").field("c3_O2");
    model.component("comp1").physics("tds3").field("concentration").component(new String[]{"c3_O2"});
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").field("pressure").field("p1");
    model.component("comp1").physics().create("dl2", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl2").field("pressure").field("p2");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").field("temperature").field("T1");
    model.component("comp1").physics().create("ht2", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht2").field("temperature").field("Tm");
    model.component("comp1").physics().create("ht3", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht3").field("temperature").field("T2");
    model.component("comp1").physics().create("g", "GeneralFormPDE", "geom1");
    model.component("comp1").physics("g").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("g").prop("Units").set("DependentVariableQuantity", "length");
    model.component("comp1").physics("g").field("dimensionless").field("ds");
    model.component("comp1").physics("g").field("dimensionless").component(new String[]{"ds"});
    model.component("comp1").physics("g").prop("Units").set("SourceTermQuantity", "velocity");
    model.component("comp1").physics("g").feature("gfeq1").set("Ga", new String[][]{{"-dsx", "-dsy", "-dsz"}});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds3", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dl2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht3", true);
    model.study("std1").feature("stat").setSolveFor("/physics/g", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("p_amb", "1.013e5[Pa]", "\u73af\u5883\u538b\u529b");
    model.param().set("p_inlet", "50[Pa]", "\u8868\u538b\uff0c\u5165\u53e3");
    model.param().set("p_outlet", "0[Pa]", "\u8868\u538b\uff0c\u51fa\u53e3");
    model.param().set("T_inlet", "550[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("T0", "T_inlet", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("M_gas", "29[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6c14\u4f53");
    model.param().set("k_l", "1e-2[m^2/(Pa*s)]", "\u96c6\u603b\u6469\u64e6\u56e0\u5b50");
    model.param().set("k_gas", "0.025[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u6c14\u4f53");
    model.param().set("Cp_gas", "1.2[kJ/(kg*K)]", "\u70ed\u5bb9\uff0c\u6c14\u4f53");
    model.param().set("k_m", "50[W/(m*K)]", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u819c");
    model.param().set("ani_m", "0.5", "\u66f2\u6298\u56e0\u5b50\uff0c\u6574\u4f53\u5f0f yz \u5e73\u9762");
    model.param().set("rho_m", "4e3[kg/m^3]", "\u5bc6\u5ea6\uff0c\u819c");
    model.param().set("Cp_m", "2[kJ/(kg*K)]", "\u70ed\u5bb9\uff0c\u819c");
    model.param().set("d_m", "200[um]", "\u819c\u539a\u5ea6");
    model.param().set("kappa_m", "1e-12[m^2]", "\u6e17\u900f\u7387\uff0c\u819c");
    model.param().set("H", "500[um]", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("eta", "3e-5[Pa*s]", "\u9ecf\u5ea6\uff0c\u6c14\u4f53");
    model.param().set("a", "4*kappa_m/(H*d_m*eta)", "\u819c\u4f20\u9012\u7cfb\u6570");
    model.param().set("T_amb", "300[K]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("ht_fa", "200[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u6ee4\u6e05\u5668/\u73af\u5883");
    model.param().set("ht_fg", "1500[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u6ee4\u6e05\u5668/\u6c14\u4f53");
    model.param().set("ds0", "25[um]", "\u521d\u59cb\u70df\u7070\u5c42\u539a\u5ea6");
    model.param().set("c_s0", "5[mmol/m^3]", "\u521d\u59cb\u70df\u7070\u6d53\u5ea6");
    model.param().set("kappa_s", "1e-13[m^2]", "\u6e17\u900f\u7387\uff0c\u70df\u7070\u5c42");
    model.param().set("rho_s", "2e3[kg/m^3]", "\u5bc6\u5ea6\uff0c\u70df\u7070\u5c42");
    model.param().set("M_s", "12[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u70df\u7070");
    model.param().set("Af", "1e13[m/s]", "\u6b63\u53cd\u5e94\u9891\u7387\u56e0\u5b50");
    model.param().set("Ef", "165e3[J/mol]", "\u53cd\u5e94\u6d3b\u5316\u80fd");
    model.param().set("H_ox", "-30[kJ/mol]", "\u53cd\u5e94\u7113");
    model.param().set("c_C_in", "0.005[mol/m^3]", "\u5165\u53e3\u7684\u70df\u7070\u6d53\u5ea6");
    model.param().set("c_O2_in", "0.08*p_amb/(R_const*T_inlet)", "\u5165\u53e3\u7684 O2 \u6d53\u5ea6");
    model.param().set("D_C", "1e-5[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u70df\u7070");
    model.param().set("D_O2", "1e-5[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cO2");
    model.param().set("D_sl", "1e-8[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u70df\u7070\u5c42");
    model.param().set("Rs_factor", "1", "\u6c27\u5316\u53cd\u5e94\u901f\u7387\u56e0\u5b50");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("rho1", "(p_amb+p1)*M_gas/(R_const*T1)", "\u6c14\u4f53\u5bc6\u5ea6\uff0c\u5165\u53e3\u901a\u9053");
    model.component("comp1").variable("var1")
         .set("rho2", "(p_amb+p2)*M_gas/(R_const*T2)", "\u6c14\u4f53\u5bc6\u5ea6\uff0c\u51fa\u53e3\u901a\u9053");
    model.component("comp1").variable("var1").set("v1", "-k_l*p1X", "\u6d41\u901f\uff0c\u5165\u53e3\u901a\u9053");
    model.component("comp1").variable("var1").set("v2", "-k_l*p2X", "\u6d41\u901f\uff0c\u51fa\u53e3\u901a\u9053");
    model.component("comp1").variable("var1")
         .set("v_m", "(H/4)*(p1-p2)*a*b/(a+b)", "\u901a\u8fc7\u819c\u7684\u8868\u9762\u6d41\u901f");
    model.component("comp1").variable("var1")
         .set("b", "4*kappa_s/(H*ds*eta)", "\u70df\u7070\u5c42\u4f20\u9012\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("showcoincident", false);
    model.component("comp1").geom("geom1").feature("wp1").set("showintersection", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1")
         .set("semiaxes", new String[]{"5.86[in]", "4.66[in]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("angle", 90);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "8[in]", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("chem").prop("TPFeatureInput").set("T_src", "root.comp1.Tm");
    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 3);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "C+O2+CexOy(ads)=>CO2+CexOy(ads)");
    model.component("comp1").physics("chem").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("r", "Rs_factor*chem.kf_1*chem.c_O2");
    model.component("comp1").physics("chem").feature("rch1").set("bulkFwdOrder", 1);
    model.component("comp1").physics("chem").feature("rch1").set("surfFwdOrder", 0);
    model.component("comp1").physics("chem").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch1").set("Af", "Af");
    model.component("comp1").physics("chem").feature("rch1").set("Ef", "Ef");
    model.component("comp1").physics("chem").feature("rch1").set("ReactionEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("H", "H_ox");
    model.component("comp1").physics("chem").feature("CexOy_surf").set("cLock", true);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "c_C_in", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", 1, 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "c1_O2", 2, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").setIndex("csurf", 1, 0, 0);
    model.component("comp1").physics("tds").prop("AdvancedSettings").set("ConvectiveTerm", "cons");
    model.component("comp1").physics("tds").feature("cdm1").set("u", new String[]{"v1", "0", "0"});
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c1_O2", new String[]{"D_O2", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("tds").create("reac1", "Reactions", 3);
    model.component("comp1").physics("tds").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds").feature("reac1")
         .setIndex("R_c1_O2", "-(4/H)*v_m*c1_O2+(4/H)*chem.Rsurf_O2", 0);
    model.component("comp1").physics("tds").create("in1", "Inflow", 2);
    model.component("comp1").physics("tds").feature("in1").selection().set(1);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "c_O2_in", 0);
    model.component("comp1").physics("tds2").prop("AdvancedSettings").set("ConvectiveTerm", "cons");
    model.component("comp1").physics("tds2").feature("cdm1").set("u", new String[]{"v1", "0", "0"});
    model.component("comp1").physics("tds2").feature("cdm1")
         .set("D_c2_C", new String[]{"D_C", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("tds2").feature("init1").setIndex("initc", "c_s0", 0);
    model.component("comp1").physics("tds2").create("reac1", "Reactions", 3);
    model.component("comp1").physics("tds2").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds2").feature("reac1").setIndex("R_c2_C", "-(4/H)*c2_C*v_m", 0);
    model.component("comp1").physics("tds2").create("conc1", "Concentration", 2);
    model.component("comp1").physics("tds2").feature("conc1").selection().set(1);
    model.component("comp1").physics("tds2").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds2").feature("conc1").setIndex("c0", "c_s0", 0);
    model.component("comp1").physics("tds3").prop("AdvancedSettings").set("ConvectiveTerm", "cons");
    model.component("comp1").physics("tds3").feature("cdm1").set("u", new String[]{"v2", "0", "0"});
    model.component("comp1").physics("tds3").feature("cdm1")
         .set("D_c3_O2", new String[]{"D_O2", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("tds3").create("reac1", "Reactions", 3);
    model.component("comp1").physics("tds3").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds3").feature("reac1").setIndex("R_c3_O2", "(4/H)*v_m*c1_O2", 0);
    model.component("comp1").physics("tds3").create("out1", "Outflow", 2);
    model.component("comp1").physics("tds3").feature("out1").selection().set(5);
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho", "rho1");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu", "eta");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", 1);
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"k_l*eta", "0", "0", "0", "k_l*eta", "0", "0", "0", "k_l*eta"});
    model.component("comp1").physics("dl").create("ms1", "MassSource", 3);
    model.component("comp1").physics("dl").feature("ms1").selection().all();
    model.component("comp1").physics("dl").feature("ms1").set("Qm", "-rho1*(p1-p2)*a*b/(a+b)");
    model.component("comp1").physics("dl").create("pr1", "Pressure", 2);
    model.component("comp1").physics("dl").feature("pr1").selection().set(1);
    model.component("comp1").physics("dl").feature("pr1").set("p0", "p_inlet");
    model.component("comp1").physics("dl2").feature("porous1").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("dl2").feature("porous1").feature("fluid1").set("rho", "rho2");
    model.component("comp1").physics("dl2").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("dl2").feature("porous1").feature("fluid1").set("mu", "eta");
    model.component("comp1").physics("dl2").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl2").feature("porous1").feature("pm1").set("epsilon", 1);
    model.component("comp1").physics("dl2").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl2").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"k_l*eta", "0", "0", "0", "k_l*eta", "0", "0", "0", "k_l*eta"});
    model.component("comp1").physics("dl2").create("ms1", "MassSource", 3);
    model.component("comp1").physics("dl2").feature("ms1").selection().set(1);
    model.component("comp1").physics("dl2").feature("ms1").set("Qm", "rho2*(p1-p2)*a*b/(a+b)");
    model.component("comp1").physics("dl2").create("pr1", "Pressure", 2);
    model.component("comp1").physics("dl2").feature("pr1").selection().set(5);
    model.component("comp1").physics("dl2").feature("pr1").set("p0", "p_outlet");
    model.component("comp1").physics("ht").feature("fluid1").set("u", new String[]{"v1", "0", "0"});
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1")
         .set("k", new String[]{"k_gas", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("rho", "rho1");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht").feature("fluid1").set("Cp", "Cp_gas");
    model.component("comp1").physics("ht").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "-(4/H)*rho1*Cp_gas*T1*v_m-(4/H)*ht_fg*(T1-Tm)");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_inlet");
    model.component("comp1").physics("ht2").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht2").feature("fluid1")
         .set("k", new String[]{"k_m", "0", "0", "0", "k_m*ani_m", "0", "0", "0", "k_m*ani_m"});
    model.component("comp1").physics("ht2").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht2").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht2").feature("fluid1").set("rho", "rho_m");
    model.component("comp1").physics("ht2").feature("fluid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht2").feature("fluid1").set("Cp", "Cp_m");
    model.component("comp1").physics("ht2").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht2").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht2").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht2").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht2").feature("hs1")
         .set("Q0", "(chem.Qs+(rho1*T1-rho2*T2)*Cp_gas*v_m-ht_fg*(2*Tm-T1-T2))/d_m");
    model.component("comp1").physics("ht2").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht2").feature("hf1").selection().set(1);
    model.component("comp1").physics("ht2").feature("hf1").set("q0_input", "-ht_fg*(Tm-T1)");
    model.component("comp1").physics("ht2").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht2").feature("hf2").selection().set(4);
    model.component("comp1").physics("ht2").feature("hf2").set("q0_input", "-ht_fa*(Tm-T_amb)");
    model.component("comp1").physics("ht2").create("hf3", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht2").feature("hf3").selection().set(5);
    model.component("comp1").physics("ht2").feature("hf3").set("q0_input", "-ht_fg*(Tm-T2)");
    model.component("comp1").physics("ht3").feature("fluid1").set("u", new String[]{"v2", "0", "0"});
    model.component("comp1").physics("ht3").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("ht3").feature("fluid1")
         .set("k", new String[]{"k_gas", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").physics("ht3").feature("fluid1").set("fluidType", "gasLiquid");
    model.component("comp1").physics("ht3").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("ht3").feature("fluid1").set("rho", "rho2");
    model.component("comp1").physics("ht3").feature("fluid1").set("Cp_mat", "userdef");
    model.component("comp1").physics("ht3").feature("fluid1").set("Cp", "Cp_gas");
    model.component("comp1").physics("ht3").feature("fluid1").set("gamma_not_IG_mat", "userdef");
    model.component("comp1").physics("ht3").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht3").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht3").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht3").feature("hs1").set("Q0", "(4/H)*rho2*Cp_gas*Tm*v_m-(4/H)*ht_fg*(T2-Tm)");
    model.component("comp1").physics("ht3").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht3").feature("ofl1").selection().set(5);
    model.component("comp1").physics("g").feature("gfeq1")
         .setIndex("Ga", new String[]{"-D_sl*dsx", "-dsy", "-dsz"}, 0);
    model.component("comp1").physics("g").feature("gfeq1")
         .setIndex("Ga", new String[]{"-D_sl*dsx", "-D_sl*dsy", "-dsz"}, 0);
    model.component("comp1").physics("g").feature("gfeq1")
         .setIndex("Ga", new String[]{"-D_sl*dsx", "-D_sl*dsy", "-D_sl*dsz"}, 0);
    model.component("comp1").physics("g").feature("gfeq1").setIndex("f", "(chem.Rsurf_C+c2_C*v_m)*M_s/rho_s", 0);
    model.component("comp1").physics("g").feature("init1").set("ds", "ds0");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 0.1);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/g", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,90,900)");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "p_amb", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Pa", 0);
    model.study("std1").feature("param").setIndex("pname", "p_amb", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Pa", 0);
    model.study("std1").feature("param").setIndex("pname", "Rs_factor", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 1", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ss3");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ss4");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ss5");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ss6");
    model.sol("sol1").feature("s1").feature("se1").feature("ss7").label("\u5206\u79bb\u6b65\u9aa4 3");
    model.sol("sol1").feature("s1").feature("se1").feature("ss8").label("\u5206\u79bb\u6b65\u9aa4 4");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_p1", "comp1_p2"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_T1", "comp1_T2", "comp1_Tm"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("subdamp", "0.9");
    model.sol("sol1").feature("s1").feature("se1").feature("ss7")
         .set("segvar", new String[]{"comp1_c1_O2", "comp1_c3_O2"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss7").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature("se1").feature("ss7").set("subdamp", "0.9");
    model.sol("sol1").feature("s1").feature("se1").feature("ss8").set("segvar", new String[]{"comp1_c2_C"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss8").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature("se1").feature("ss8").set("subdamp", "1");
    model.sol("sol1").feature("s1").feature("se1").feature("ll1")
         .set("lowerlimit", "comp1.T1 200 comp1.T2 200 comp1.Tm 200");
    model.sol("sol1").feature("t1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("se1").feature().remove("ss4");
    model.sol("sol1").feature("t1").feature("se1").feature().remove("ss5");
    model.sol("sol1").feature("t1").feature("se1").feature().remove("ss6");
    model.sol("sol1").feature("t1").feature("se1").feature().remove("ss7");
    model.sol("sol1").feature("t1").feature("se1").feature("ss8").label("\u5206\u79bb\u6b65\u9aa4 4");
    model.sol("sol1").feature("t1").feature("se1").feature("ss9").label("\u5206\u79bb\u6b65\u9aa4 5");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_p1", "comp1_p2"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_T1", "comp1_T2", "comp1_Tm"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subdamp", "0.7");
    model.sol("sol1").feature("t1").feature("se1").feature("ss3")
         .set("segvar", new String[]{"comp1_c1_O2", "comp1_c3_O2"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss3").set("subdamp", "0.7");
    model.sol("sol1").feature("t1").feature("se1").feature("ss3").set("subiter", 2);
    model.sol("sol1").feature("t1").feature("se1").feature("ss9").set("segvar", new String[]{"comp1_ds"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss9").set("linsolver", "d1");
    model.sol("sol1").feature("t1").feature("se1").feature("ss9").set("subdamp", "1");
    model.sol("sol1").feature("t1").feature("se1").feature("ll1")
         .set("lowerlimit", "comp1.T1 200 comp1.T2 200 comp1.Tm 200");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u6c14\u4f53\u901f\u5ea6\uff0c\u5165\u53e3");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u65f6\u95f4=0 s\uff0c\u8868\u9762\uff1a\u901f\u5ea6(m/s)\uff0c\u53d1\u751f\u6c27\u5316");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "dl.U");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u6c14\u4f53\u901f\u5ea6\uff0c\u51fa\u53e3");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "\u65f6\u95f4=0 s\uff0c\u8868\u9762\uff1a\u901f\u5ea6(m/s)\uff0c\u53d1\u751f\u6c27\u5316");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "dl2.U");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u6d53\u5ea6\uff0cO2\uff0c\u5165\u53e3");
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u6d53\u5ea6\uff0cC\uff0c\u819c");
    model.result("pg4").create("slc1", "Slice");
    model.result("pg4").feature("slc1").set("expr", "c2_C");
    model.result("pg4").feature("slc1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc2_C");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u6d53\u5ea6\uff0cO2\uff0c\u51fa\u53e3");
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("expr", "c3_O2");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u6e29\u5ea6 Tm\uff0c\u65e0\u6c27\u5316");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 1, 1);
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u65f6\u95f4=900 s \u8868\u9762\uff1a\u6e29\u5ea6(K)\uff0c\u65e0\u6c27\u5316");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "Tm");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u6e29\u5ea6 Tm\uff0c\u53d1\u751f\u6c27\u5316");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u65f6\u95f4=900 s \u8868\u9762\uff1a\u6e29\u5ea6(K)\uff0c\u53d1\u751f\u6c27\u5316");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "Tm");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u6cbf\u4e2d\u5fc3\u7ebf\u7684\u538b\u5dee\uff0c\u65e0\u6c27\u5316\u53cd\u5e94");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevelinput", "manual", 1);
    model.result("pg8").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg8").setIndex("looplevelinput", "manual", 0);
    model.result("pg8").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").selection().set(3);
    model.result("pg8").feature("lngr1").set("expr", "p1-p2");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9")
         .label("\u6cbf\u4e2d\u5fc3\u7ebf\u7684\u70df\u7070\u5c42\u539a\u5ea6\uff0c\u65e0\u6c27\u5316\u53cd\u5e94");
    model.result("pg9").setIndex("looplevelinput", "all", 0);
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").set("expr", "ds");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").feature("lngr1").set("unit", "\u00b5m");
    model.result("pg9").feature("lngr1").create("col1", "Color");
    model.result("pg9").run();
    model.result("pg9").feature("lngr1").feature("col1").set("expr", "t");
    model.result("pg9").feature("lngr1").feature("col1").set("titletype", "auto");
    model.result("pg9").feature("lngr1").feature("col1").set("colortable", "SpectrumClassic");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u70df\u7070\u5c42 ds\uff0c\u6cbf\u9876\u7ebf");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr1").set("linewidth", "preference");
    model.result("pg10").feature("lngr1").selection().set(5);
    model.result("pg10").feature("lngr1").set("expr", "ds");
    model.result("pg10").feature("lngr1").feature().copy("col1", "pg9/lngr1/col1");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u6cbf\u4e2d\u5fc3\u7ebf\u7684\u70df\u7070\u5c42 ds");
    model.result("pg11").run();
    model.result("pg11").feature("lngr1").selection().set(3);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").label("\u6cbf\u4e2d\u5fc3\u7ebf 1 \u7684\u538b\u5dee p1-p2");
    model.result("pg12").run();
    model.result("pg12").feature("lngr1").set("expr", "p1-p2");
    model.result("pg12").run();

    model.title("\u67f4\u6cb9\u9897\u7c92\u8fc7\u6ee4\u5668\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u8026\u5408\u7684\u8d28\u91cf\u4f20\u9012\u3001\u4f20\u70ed\u548c\u52a8\u91cf\u4f20\u9012\u63a5\u53e3\u5bf9\u67f4\u6cb9\u9897\u7c92\u8fc7\u6ee4\u5668\u8fdb\u884c\u4e09\u7ef4\u5efa\u6a21\u3002\u6a21\u578b\u5305\u542b\u591a\u5b54\u58c1\uff0c\u6c14\u4f53\u53ef\u4ee5\u5728\u5176\u4e2d\u901a\u8fc7\uff0c\u540c\u65f6\u4ece\u6d41\u4f53\u4e2d\u5206\u79bb\u51fa\u9897\u7c92\uff0c\u53ef\u4ee5\u7814\u7a76\u5e73\u5747\u6d53\u5ea6\u3001\u6e29\u5ea6\u548c\u6d41\u573a\u3002\u5728\u52a8\u6001\u8868\u793a\u8fc7\u6ee4\u5668\u4e2d\u7684\u70df\u5c18\u5806\u79ef\u65f6\uff0c\u52a0\u5165\u4e86\u7c92\u5b50\u6c89\u79ef\u4ee5\u53ca\u78b3\u7684\u50ac\u5316\u6c27\u5316\u4f5c\u7528\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("diesel_particulate_filter.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
