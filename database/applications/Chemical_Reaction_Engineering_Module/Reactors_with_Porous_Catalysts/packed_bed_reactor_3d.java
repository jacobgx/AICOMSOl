/*
 * packed_bed_reactor_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:26 by COMSOL 6.3.0.290. */
public class packed_bed_reactor_3d {

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
    model.component("comp1").physics().create("tds", "DilutedSpeciesInPorousMedia", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("cA");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cA", "cB", "cC"});
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/chem", true);
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho_b", "0.51[g/cm^3]", "\u586b\u5145\u5e8a\u5bc6\u5ea6");
    model.param().set("rho_pe", "0.68[g/cm^3]", "\u5355\u9897\u7c92\u5bc6\u5ea6");
    model.param().set("epsilon_b", "1-rho_b/rho_pe", "\u586b\u5145\u5e8a\u7684\u5b54\u9699\u7387");
    model.param().set("epsilon_pe", "0.70[1]", "\u9897\u7c92\u7684\u5b54\u9699\u7387");
    model.param().set("r_pe", "5e-4[m]", "\u9897\u7c92\u534a\u5f84\uff08\u7403\u5f62\uff09");
    model.param().set("DAp", "1.5e-9[m^2/s]", "\u9897\u7c92\u4e2d A \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DBp", "2e-9[m^2/s]", "\u9897\u7c92\u4e2d B \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DCp", "1e-9[m^2/s]", "\u9897\u7c92\u4e2d C \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("kappa", "1.88e-10[m^2]", "\u586b\u5145\u5e8a\u7684\u6e17\u900f\u7387");
    model.param().set("CA_in", "1[mol/m^3]", "A \u7684\u5165\u53e3\u6d53\u5ea6");
    model.param().set("CB_in", "1[mol/m^3]", "B \u7684\u5165\u53e3\u6d53\u5ea6");
    model.param().set("CC_in", "0[mol/m^3]", "C \u7684\u5165\u53e3\u6d53\u5ea6");
    model.param().set("DA", "1e-8[m^2/s]", "\u586b\u5145\u5e8a\u4e2d A \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DB", "1.5e-8[m^2/s]", "\u586b\u5145\u5e8a\u4e2d B \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DC", "0.5e-8[m^2/s]", "\u586b\u5145\u5e8a\u4e2d C \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("Mn_A", "0.018[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cA");
    model.param().set("Mn_B", "0.020[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cB");
    model.param().set("Mn_C", "0.019[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cC");
    model.param().set("E", "75000[J/mol]", "\u6d3b\u5316\u80fd");
    model.param().set("A", "2e12[m^3/(mol*s)]", "\u9891\u7387\u56e0\u5b50");
    model.param().set("Keq0", "1000", "\u5e73\u8861\u5e38\u6570");
    model.param().set("p_Darcy", "0.4[atm]", "\u5165\u53e3\u538b\u529b\u504f\u79fb\u91cf");
    model.param().set("Mn_solvent", "0.018[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6eb6\u5242");
    model.param().set("C_solvent", "998[kg/m^3]/Mn_solvent", "\u6d53\u5ea6\uff0c\u6eb6\u5242");

    model.component("comp1").geom("geom1").insertFile("packed_bed_reactor_3d_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.material("mat1").label("Water, liquid");
    model.material("mat1").set("family", "water");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.material("mat1").propertyGroup("def").func("an2").set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an3").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").feature().create("pellet1", "Pellet", "comp1");
    model.component("comp1").material("pmat1").feature("pellet1").set("link", "none");
    model.component("comp1").material("pmat1").feature("pellet1").set("dpes", "r_pe*2");
    model.component("comp1").material("pmat1").feature("pellet1").set("epspe", "epsilon_pe");
    model.component("comp1").material("pmat1").feature("pellet1").set("epsb", "epsilon_b");
    model.component("comp1").material("pmat1").feature("pellet1").set("pelletDisType", "linear");
    model.component("comp1").material("pmat1").feature("pellet1").set("nelems", 12);
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");

    model.component("comp1").physics("tds").create("pb1", "PackedBed", 3);
    model.component("comp1").physics("tds").feature("pb1").selection().all();
    model.component("comp1").physics("chem").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 3);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "A+B<=>2C");
    model.component("comp1").physics("chem").feature("rch1").set("setKeq0", true);
    model.component("comp1").physics("chem").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch1").set("Af", "A");
    model.component("comp1").physics("chem").feature("rch1").set("Ef", "E");
    model.component("comp1").physics("chem").feature("rch1").set("EquilibriumConstantSettings", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("Keq0", "Keq0");
    model.component("comp1").physics("chem").feature("A").set("M", "Mn_A");
    model.component("comp1").physics("chem").feature("B").set("M", "Mn_B");
    model.component("comp1").physics("chem").feature("C").set("M", "Mn_C");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 3);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("chem").feature("H2O").set("sType", "solvent");
    model.component("comp1").physics("chem").feature("H2O").set("M", "Mn_solvent");
    model.component("comp1").physics("chem").prop("xdim").set("xdim", true);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "tds.cpe_cA", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "tds.cpe_cB", 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "tds.cpe_cC", 2, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "C_solvent", 3, 0);
    model.component("comp1").physics("tds").feature("pb1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("tds").feature("pb1").feature("fluid1")
         .set("DF_cA", new String[]{"DA", "0", "0", "0", "DA", "0", "0", "0", "DA"});
    model.component("comp1").physics("tds").feature("pb1").feature("fluid1")
         .set("DF_cB", new String[]{"DB", "0", "0", "0", "DB", "0", "0", "0", "DB"});
    model.component("comp1").physics("tds").feature("pb1").feature("fluid1")
         .set("DF_cC", new String[]{"DC", "0", "0", "0", "DC", "0", "0", "0", "DC"});
    model.component("comp1").physics("tds").feature("pb1").feature("fluid1")
         .set("FluidDiffusivityModelType", "UserDefined");
    model.component("comp1").physics("tds").feature("pb1").feature("pts1").feature("df1")
         .set("diffusionmodel", "UserDefined");
    model.component("comp1").physics("tds").feature("pb1").feature("pts1").feature("df1")
         .setIndex("Dpeff_cA", "DAp", 0);
    model.component("comp1").physics("tds").feature("pb1").feature("pts1").feature("df1")
         .setIndex("Dpeff_cB", "DBp", 0);
    model.component("comp1").physics("tds").feature("pb1").feature("pts1").feature("df1")
         .setIndex("Dpeff_cC", "DCp", 0);
    model.component("comp1").physics("tds").feature("pb1").feature("pts1").feature("reac1")
         .set("R_cA_src", "root.comp1.chem.R_A");
    model.component("comp1").physics("tds").feature("pb1").feature("pts1").feature("reac1")
         .set("R_cB_src", "root.comp1.chem.R_B");
    model.component("comp1").physics("tds").feature("pb1").feature("pts1").feature("reac1")
         .set("R_cC_src", "root.comp1.chem.R_C");
    model.component("comp1").physics("tds").feature("pb1").feature("pts1").feature("reac1")
         .set("ReactingVolumeType", "TotalVolume");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("smooth", 1);
    model.component("comp1").func("step1").set("location", "0.5[s]");

    model.component("comp1").physics("tds").create("in1", "Inflow", 2);
    model.component("comp1").physics("tds").feature("in1").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "CA_in*step1(t)", 0);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "CB_in*step1(t)", 1);
    model.component("comp1").physics("tds").feature("in1").setIndex("c0", "CC_in*step1(t)", 2);
    model.component("comp1").physics("tds").create("out1", "Outflow", 2);
    model.component("comp1").physics("tds").feature("out1").selection().named("geom1_sel2");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa", "0", "0", "0", "kappa", "0", "0", "0", "kappa"});
    model.component("comp1").physics("dl").create("pr1", "Pressure", 2);
    model.component("comp1").physics("dl").feature("pr1").selection().named("geom1_sel2");
    model.component("comp1").physics("dl").create("pr2", "Pressure", 2);
    model.component("comp1").physics("dl").feature("pr2").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("dl").feature("pr2").set("p0", "p_Darcy");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_csel2_bnd");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 15);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,10,180)");
    model.study("std1").feature("time").setSolveFor("/physics/dl", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", false);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").feature().move("stat", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 19, 0);
    model.result("pg1").label("\u5e8a\u6d53\u5ea6, A, \u6d41\u7ebf (tds)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"tds.tflux_cAx", "tds.tflux_cAy", "tds.tflux_cAz"});
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").feature("str1").create("col", "Color");
    model.result("pg1").feature("str1").feature("col").set("expr", "cA");
    model.result("pg1").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg1").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg1").feature("str1").set("linetype", "ribbon");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 19, 0);
    model.result("pg2").label("\u5e8a\u6d53\u5ea6, A, \u8868\u9762 (tds)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("prefixintitle", "");
    model.result("pg2").set("expressionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"cA"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 19, 0);
    model.result("pg3").label("\u5e8a\u6d53\u5ea6, B, \u6d41\u7ebf (tds)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"tds.tflux_cBx", "tds.tflux_cBy", "tds.tflux_cBz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col", "Color");
    model.result("pg3").feature("str1").feature("col").set("expr", "cB");
    model.result("pg3").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg3").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg3").feature("str1").set("linetype", "ribbon");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 19, 0);
    model.result("pg4").label("\u5e8a\u6d53\u5ea6, B, \u8868\u9762 (tds)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cB"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 19, 0);
    model.result("pg5").label("\u5e8a\u6d53\u5ea6, C, \u6d41\u7ebf (tds)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"tds.tflux_cCx", "tds.tflux_cCy", "tds.tflux_cCz"});
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg5").feature("str1").create("col", "Color");
    model.result("pg5").feature("str1").feature("col").set("expr", "cC");
    model.result("pg5").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg5").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg5").feature("str1").set("linetype", "ribbon");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 19, 0);
    model.result("pg6").label("\u5e8a\u6d53\u5ea6, C, \u8868\u9762 (tds)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cC"});
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").set("solution", "sol1");
    model.result().dataset("dset3").set("comp", "pmat1_pellet1_xdim");
    model.result().dataset("dset3").label("\u7814\u7a76 1/\u89e3 1 (3) \u9897\u7c92 1");
    model.result().numerical().create("eval1", "Eval");
    model.result().numerical("eval1").set("data", "dset3");
    model.result().numerical("eval1")
         .set("expr", "comp1.atxd3(0.075[m], 0.0989531564281315[m], 0.04098773943215847[m], tds.cpe_cA)");
    model.result().numerical("eval1").run();
    model.result().numerical().remove("eval1");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u201c(0.075[m], 0.098953[m], 0.040988[m])\u201d\u7684\u9897\u7c92\u6d53\u5ea6");
    model.result("pg7")
         .label("\u201c(0.075[m], 0.098953[m], 0.040988[m])\u201d\u7684\u9897\u7c92\u6d53\u5ea6 (tds)");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x_pmat1_pellet1_xdim");
    model.result("pg7").feature("lngr1").selection().geom("pmat1_pellet1_xdim", 1);
    model.result("pg7").feature("lngr1").selection().set(1);
    model.result("pg7").feature("lngr1")
         .set("expr", new String[]{"atxd3(0.075[m], 0.098953[m], 0.040988[m], tds.cpe_cA)"});
    model.result("pg7").feature("lngr1").label("\u7269\u8d28 A");
    model.result("pg7").feature("lngr1").set("descractive", true);
    model.result("pg7").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "tds.pmat1_pellet1_r");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("autosolution", false);
    model.result("pg7").feature("lngr1").set("autoexpr", false);
    model.result("pg7").feature("lngr1").set("autodescr", false);
    model.result("pg7").feature("lngr1").set("legendprefix", "A ");
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr2").set("xdata", "expr");
    model.result("pg7").feature("lngr2").set("xdataexpr", "x_pmat1_pellet1_xdim");
    model.result("pg7").feature("lngr2").selection().geom("pmat1_pellet1_xdim", 1);
    model.result("pg7").feature("lngr2").selection().set(1);
    model.result("pg7").feature("lngr2")
         .set("expr", new String[]{"atxd3(0.075[m], 0.098953[m], 0.040988[m], tds.cpe_cB)"});
    model.result("pg7").feature("lngr2").label("\u7269\u8d28 B");
    model.result("pg7").feature("lngr2").set("descractive", true);
    model.result("pg7").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg7").feature("lngr2").set("xdata", "expr");
    model.result("pg7").feature("lngr2").set("xdataexpr", "tds.pmat1_pellet1_r");
    model.result("pg7").feature("lngr2").set("legend", true);
    model.result("pg7").feature("lngr2").set("autosolution", false);
    model.result("pg7").feature("lngr2").set("autoexpr", false);
    model.result("pg7").feature("lngr2").set("autodescr", false);
    model.result("pg7").feature("lngr2").set("legendprefix", "B ");
    model.result("pg7").create("lngr3", "LineGraph");
    model.result("pg7").feature("lngr3").set("xdata", "expr");
    model.result("pg7").feature("lngr3").set("xdataexpr", "x_pmat1_pellet1_xdim");
    model.result("pg7").feature("lngr3").selection().geom("pmat1_pellet1_xdim", 1);
    model.result("pg7").feature("lngr3").selection().set(1);
    model.result("pg7").feature("lngr3")
         .set("expr", new String[]{"atxd3(0.075[m], 0.098953[m], 0.040988[m], tds.cpe_cC)"});
    model.result("pg7").feature("lngr3").label("\u7269\u8d28 C");
    model.result("pg7").feature("lngr3").set("descractive", true);
    model.result("pg7").feature("lngr3").set("descr", "\u6d53\u5ea6");
    model.result("pg7").feature("lngr3").set("xdata", "expr");
    model.result("pg7").feature("lngr3").set("xdataexpr", "tds.pmat1_pellet1_r");
    model.result("pg7").feature("lngr3").set("legend", true);
    model.result("pg7").feature("lngr3").set("autosolution", false);
    model.result("pg7").feature("lngr3").set("autoexpr", false);
    model.result("pg7").feature("lngr3").set("autodescr", false);
    model.result("pg7").feature("lngr3").set("legendprefix", "C ");
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "dset1");
    model.result().dataset("cpt1").set("method", "regulargrid");
    model.result().dataset("cpt1").set("regulargridx", 10);
    model.result().dataset("cpt1").set("regulargridy", 6);
    model.result().dataset("cpt1").set("regulargridz", 4);
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u9897\u7c92\u6d53\u5ea6, A");
    model.result("pg8").create("pel1", "Pellets");
    model.result("pg8").feature("pel1").set("data", "cpt1");
    model.result("pg8").feature("pel1").set("material", "1");
    model.result("pg8").feature("pel1").set("expr", "tds.cpe_cA");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u9897\u7c92\u6d53\u5ea6, B");
    model.result("pg9").create("pel1", "Pellets");
    model.result("pg9").feature("pel1").set("data", "cpt1");
    model.result("pg9").feature("pel1").set("material", "1");
    model.result("pg9").feature("pel1").set("expr", "tds.cpe_cB");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u9897\u7c92\u6d53\u5ea6, C");
    model.result("pg10").create("pel1", "Pellets");
    model.result("pg10").feature("pel1").set("data", "cpt1");
    model.result("pg10").feature("pel1").set("material", "1");
    model.result("pg10").feature("pel1").set("expr", "tds.cpe_cC");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").label("\u901f\u5ea6 (dl)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").feature().create("str1", "Streamline");
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("expr", new String[]{"dl.u", "dl.v", "dl.w"});
    model.result("pg11").feature("str1").set("posmethod", "start");
    model.result("pg11").feature("str1").set("pointtype", "arrow");
    model.result("pg11").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("str1").set("smooth", "internal");
    model.result("pg11").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("data", "parent");
    model.result("pg11").feature("str1").selection().geom("geom1", 2);
    model.result("pg11").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    model.result("pg11").feature("str1").feature().create("col1", "Color");
    model.result("pg11").feature("str1").feature("col1").set("expr", "dl.U");
    model.result("pg11").feature("str1").feature("col1").set("colortable", "Rainbow");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").label("\u538b\u529b (dl)");
    model.result("pg12").feature().create("surf1", "Surface");
    model.result("pg12").feature("surf1").label("\u8868\u9762");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("expr", "p");
    model.result("pg12").feature("surf1").set("colortable", "Rainbow");
    model.result("pg12").feature("surf1").set("smooth", "internal");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.view().create("view6", 3);
    model.view("view6").label("\u586b\u5145\u5854\u89c6\u56fe");
    model.view().create("view7", 3);
    model.view("view7").label("\u9897\u7c92\u89c6\u56fe");

    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("sec1").setIndex("genpoints", 0, 1, 2);
    model.result().dataset("sec1").set("sectors", 8);
    model.result().dataset("sec1").set("include", "manual");
    model.result().dataset("sec1").set("sectorsinclude", 5);
    model.result().dataset().create("sec2", "Sector3D");
    model.result().dataset("sec2").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("sec2").setIndex("genpoints", 0, 1, 2);
    model.result().dataset("sec2").set("sectors", 8);
    model.result().dataset("sec2").set("include", "manual");
    model.result().dataset("sec2").set("startsector", 6);
    model.result().dataset("sec2").set("sectorsinclude", 3);
    model.result().dataset().create("cpt2", "CutPoint3D");
    model.result().dataset("cpt2").set("data", "sec2");
    model.result().dataset("cpt2").set("method", "regulargrid");
    model.result().dataset("cpt2").set("regulargridy", 12);
    model.result().dataset("cpt2").set("regulargridz", 8);
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").run();
    model.result("pg13").label("\u901f\u5ea6");
    model.result("pg13").set("data", "sec1");
    model.result("pg13").set("titletype", "none");
    model.result("pg13").set("showlegendsunit", true);
    model.result("pg13").set("view", "view6");
    model.result("pg13").create("slc1", "Slice");
    model.result("pg13").feature("slc1").set("expr", "dl.U");
    model.result("pg13").feature("slc1").set("descr", "\u603b\u8fbe\u897f\u901f\u5ea6\u5927\u5c0f");
    model.result("pg13").feature("slc1").set("quickxnumber", 10);
    model.result("pg13").feature("slc1").set("colortable", "Cividis");
    model.result("pg13").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "sec1");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").set("view", "view6");
    model.result("pg2").run();
    model.result("pg7").run();
    model.result("pg7").label("(0.5[m]\u30010[m]\u30010[m]) \u65f6\u7684\u9897\u7c92\u6d53\u5ea6 (tds)");
    model.result("pg7").setIndex("looplevelinput", "last", 0);
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("twoyaxes", true);
    model.result("pg7").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg7").set("legendpos", "middleleft");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").set("expr", "atxd3(0.5[m], 0[m], 0[m], tds.cpe_cA)");
    model.result("pg7").feature("lngr1").set("linewidth", 2);
    model.result("pg7").feature("lngr1").set("legendprefix", "c<sub>A</sub>");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").set("expr", "atxd3(0.5[m], 0[m], 0[m], tds.cpe_cB)");
    model.result("pg7").feature("lngr2").set("linewidth", 2);
    model.result("pg7").feature("lngr2").set("legendprefix", "c<sub>B</sub>");
    model.result("pg7").run();
    model.result("pg7").feature("lngr3").set("expr", "atxd3(0.5[m], 0[m], 0[m], tds.cpe_cC)");
    model.result("pg7").feature("lngr3").set("linewidth", 2);
    model.result("pg7").feature("lngr3").set("legendprefix", "c<sub>C</sub>");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg14", "pg8");
    model.result("pg14").run();
    model.result("pg14").set("data", "sec2");
    model.result("pg14").set("titletype", "none");
    model.result("pg14").set("showlegendsunit", true);
    model.result("pg14").run();
    model.result("pg14").feature("pel1").set("data", "cpt2");
    model.result("pg14").feature("pel1").set("solutionparams", "parent");
    model.result("pg14").feature("pel1").set("radiusscaleactive", true);
    model.result("pg14").feature("pel1").set("radiusscale", 25);
    model.result("pg14").run();
    model.result("pg14").label("\u9897\u7c92\u548c\u5e8a\u5c42\u6d53\u5ea6\uff0cA");
    model.result("pg14").set("edges", false);
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("data", "dset1");
    model.result("pg14").feature("surf1").create("sel1", "Selection");
    model.result("pg14").feature("surf1").feature("sel1").selection().set(3);
    model.result("pg14").run();
    model.result("pg14").feature("surf1").set("inheritplot", "pel1");
    model.result("pg14").run();
    model.result("pg14").create("surf2", "Surface");
    model.result("pg14").feature("surf2").label("\u8868\u9762\uff1a\u5916\u58c1");
    model.result("pg14").feature("surf2").set("expr", "1");
    model.result("pg14").feature("surf2").set("titletype", "none");
    model.result("pg14").feature("surf2").set("coloring", "uniform");
    model.result("pg14").feature("surf2").set("color", "gray");
    model.result("pg14").feature("surf2").create("sel1", "Selection");
    model.result("pg14").feature("surf2").feature("sel1").selection().set(4, 12);
    model.result("pg14").run();
    model.result("pg14").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg14").run();
    model.result("pg14").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg14").feature("surf2").feature("mtrl1").set("family", "steelanodized");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result().duplicate("pg15", "pg14");
    model.result("pg15").run();
    model.result("pg15").label("\u9897\u7c92\u548c\u5e8a\u6d53\u5ea6\uff0cB");
    model.result("pg15").run();
    model.result("pg15").feature("pel1").set("expr", "tds.cpe_cB");
    model.result("pg15").feature("pel1").set("descr", "\u9897\u7c92\u4e2d\u6d53\u5ea6");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").set("expr", "cB");
    model.result("pg15").run();
    model.result().duplicate("pg16", "pg15");
    model.result("pg16").run();
    model.result("pg16").label("\u9897\u7c92\u548c\u5e8a\u6d53\u5ea6\uff0cC");
    model.result("pg16").run();
    model.result("pg16").feature("pel1").set("expr", "tds.cpe_cC");
    model.result("pg16").feature("pel1").set("descr", "\u9897\u7c92\u4e2d\u6d53\u5ea6");
    model.result("pg16").run();
    model.result("pg16").feature("surf1").set("expr", "cC");
    model.result("pg16").run();
    model.result("pg14").run();
    model.result().move("pg14", 0);
    model.result().move("pg15", 1);
    model.result().move("pg16", 2);
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().create("pg17", "PlotGroup1D");
    model.result("pg17").run();
    model.result("pg17").label("\u6d53\u5ea6\u6bd4\u8f83");
    model.result("pg17").set("data", "cln1");
    model.result("pg17").setIndex("looplevelinput", "last", 0);
    model.result("pg17").set("titletype", "none");
    model.result("pg17").set("xlabelactive", true);
    model.result("pg17").set("xlabel", "\u53cd\u5e94\u5668\u9ad8\u5ea6 (m)");
    model.result("pg17").set("ylabelactive", true);
    model.result("pg17").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg17").set("legendpos", "middleright");
    model.result("pg17").create("lngr1", "LineGraph");
    model.result("pg17").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg17").feature("lngr1").set("linewidth", "preference");
    model.result("pg17").feature("lngr1").label("A\uff0c\u5e8a");
    model.result("pg17").feature("lngr1").set("linestyle", "dashed");
    model.result("pg17").feature("lngr1").set("linewidth", 2);
    model.result("pg17").feature("lngr1").set("linemarker", "point");
    model.result("pg17").feature("lngr1").set("markerpos", "interp");
    model.result("pg17").feature("lngr1").set("markers", 11);
    model.result("pg17").feature("lngr1").set("legend", true);
    model.result("pg17").feature("lngr1").set("legendmethod", "manual");
    model.result("pg17").feature("lngr1").setIndex("legends", "c<sub>A</sub>", 0);
    model.result("pg17").feature().duplicate("lngr2", "lngr1");
    model.result("pg17").run();
    model.result("pg17").feature("lngr2").label("B\uff0c\u5e8a");
    model.result("pg17").feature("lngr2").set("expr", "cB");
    model.result("pg17").feature("lngr2").set("linemarker", "none");
    model.result("pg17").feature("lngr2").setIndex("legends", "c<sub>B</sub>", 0);
    model.result("pg17").feature().duplicate("lngr3", "lngr2");
    model.result("pg17").run();
    model.result("pg17").feature("lngr3").label("C\uff0c\u5e8a");
    model.result("pg17").feature("lngr3").set("expr", "cC");
    model.result("pg17").feature("lngr3").setIndex("legends", "c<sub>C</sub>", 0);
    model.result("pg17").feature().duplicate("lngr4", "lngr3");
    model.result("pg17").run();
    model.result("pg17").feature("lngr4").label("A\uff0c\u9897\u7c92");
    model.result("pg17").feature("lngr4").set("expr", "tds.pb1.pts1.avecpe_cA");
    model.result("pg17").feature("lngr4").set("linestyle", "solid");
    model.result("pg17").feature("lngr4").set("linecolor", "cyclereset");
    model.result("pg17").feature("lngr4")
         .setIndex("legends", "c<sub>A</sub>\uff0c\u9897\u7c92\u5e73\u5747\u503c", 0);
    model.result("pg17").feature().duplicate("lngr5", "lngr4");
    model.result("pg17").run();
    model.result("pg17").feature("lngr5").label("B\uff0c\u9897\u7c92");
    model.result("pg17").feature("lngr5").set("expr", "tds.pb1.pts1.avecpe_cB");
    model.result("pg17").feature("lngr5").set("linecolor", "cycle");
    model.result("pg17").feature("lngr5")
         .setIndex("legends", "c<sub>B</sub>\uff0c\u9897\u7c92\u5e73\u5747\u503c", 0);
    model.result("pg17").feature().duplicate("lngr6", "lngr5");
    model.result("pg17").run();
    model.result("pg17").feature("lngr6").label("C\uff0c\u9897\u7c92");
    model.result("pg17").feature("lngr6").set("expr", "tds.pb1.pts1.avecpe_cC");
    model.result("pg17").feature("lngr6")
         .setIndex("legends", "c<sub>C</sub>\uff0c\u9897\u7c92\u5e73\u5747\u503c", 0);
    model.result("pg17").run();
    model.result("pg1").run();
    model.result("pg1").set("data", "sec1");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").set("view", "view6");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "sec1");
    model.result().dataset("cpl1").set("quickx", 0.005);
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("number", 1000);
    model.result("pg1").feature("str1").set("startdata", "cpl1");
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("radiusexpr", "cA[m^4/mol]");
    model.result("pg1").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("str1").set("tuberadiusscale", ".004");
    model.result("pg1").feature("str1").set("pointtype", "none");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result().remove("pg3");
    model.result().remove("pg5");
    model.result().remove("pg8");
    model.result().remove("pg9");
    model.result().remove("pg10");
    model.result().remove("pg11");
    model.result("pg4").run();
    model.result("pg14").run();

    model.title("\u586b\u5145\u5e8a\u53cd\u5e94\u5668\u591a\u5c3a\u5ea6\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u7a00\u7269\u8d28\u4f20\u9012\u201d\u63a5\u53e3\u6709\u6548\u5730\u6c42\u89e3\u5b8f\u89c2\u548c\u5fae\u89c2\uff08\u591a\u5c3a\u5ea6\uff09\u7ec4\u5408\u95ee\u9898\uff1a\u5177\u6709\u53cc\u5cf0\u5b54\u9699\u5206\u5e03\u7684\u5f02\u6784\u7cfb\u7edf\u3002\u5176\u4e2d\u4f7f\u7528\u7684\u201c\u586b\u5145\u5e8a\u201d\u7279\u5f81\u901a\u8fc7\u989d\u5916\u7ef4\u5ea6\u6765\u8868\u793a\u586b\u5145\u53cd\u5e94\u5668\u7684\u591a\u5b54\u50ac\u5316\u5242\u9897\u7c92\u3002\u672c\u4f8b\u4f7f\u7528\u6b64\u7279\u5f81\u6c42\u89e3\u9897\u7c92\u5185\u7684\u6d53\u5ea6\u4ee5\u53ca\u9897\u7c92\u5468\u56f4\u6d41\u4f53\u4e2d\u7684\u6d53\u5ea6\u5206\u5e03\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("packed_bed_reactor_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
