/*
 * ship_hull.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:42 by COMSOL 6.3.0.290. */
public class ship_hull {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Cathodic_Protection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cp", "CathodicProtection", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cp", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "ship_hull_geometry.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").set("fin", 8, 9, 10, 11, 14);
    model.component("comp1").geom("geom1").run("mcf1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("E_control", "-0.85[V]", "\u8239\u4f53\u7535\u4f4d vs. Ag/AgCl\uff0c\u7531 ICCP \u7cfb\u7edf\u65bd\u52a0");
    model.param().set("sigma", "4[S/m]", "\u6d77\u6c34\u7535\u5bfc\u7387");
    model.param().set("ilim", "5[A/m^2]", "\u9634\u6781\u6781\u9650\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(20, 21, 22);
    model.component("comp1").selection("sel1").label("\u87ba\u65cb\u6868\u5ea7");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(14, 16, 17, 40, 41);
    model.component("comp1").selection("sel2").label("\u87ba\u65cb\u6868\u53f6\u7247");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(29, 39);
    model.component("comp1").selection("sel3").label("\u8f74");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(19);
    model.component("comp1").selection("sel4").label("\u9633\u6781");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(18);
    model.component("comp1").selection("sel5").label("\u53c2\u6bd4\u7535\u6781");
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6")
         .set(6, 7, 8, 9, 10, 11, 12, 13, 15, 20, 21, 22, 23, 24, 25, 26, 27, 28, 30, 31, 32, 33, 34, 35, 36, 37, 38, 42, 43, 44, 45, 46, 47);
    model.component("comp1").selection("sel6").label("\u8239\u8eab\u8868\u9762");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel3", "sel4", "sel5", "sel6"});
    model.component("comp1").selection("uni1").label("\u8239\u8868\u9762");
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").set("entitydim", 2);
    model.component("comp1").selection("uni2").set("input", new String[]{"sel1", "sel2", "sel3"});
    model.component("comp1").selection("uni2").label("\u87ba\u65cb\u6868\u548c\u8f74");
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").set("entitydim", 2);
    model.component("comp1").selection("uni3").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").selection("uni3").label("\u87ba\u65cb\u6868");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("LocalCurrentDensity", "LocalCurrentDensity", "Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat1").label("Alloy 625 in seawater at 30 C");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").label("Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("funcname", "iloc_exp");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("table", new String[][]{{"-1.011527576", "-0.645737289"}, 
         {"-0.947975783", "-0.352765442"}, 
         {"-0.896778481", "-0.213603782"}, 
         {"-0.85086506", "-0.127686579"}, 
         {"-0.806721138", "-0.07933071"}, 
         {"-0.752016834", "-0.05256182"}, 
         {"-0.67620161", "-0.041697665"}, 
         {"-0.598616888", "-0.031826824"}, 
         {"-0.533359585", "-0.023373034"}, 
         {"-0.476893166", "-0.015486174"}, 
         {"-0.425700785", "-0.009621443"}, 
         {"-0.383309133", "-0.005677929"}, 
         {"-0.349735437", "-0.003482567"}, 
         {"-0.319683508", "-0.002108737"}, 
         {"-0.287839933", "-0.001094224"}, 
         {"-0.266598578", "-0.000662566"}, 
         {"-0.245384294", "-0.000462173"}, 
         {"-0.227679472", "-0.000298442"}, 
         {"-0.204680924", "-0.00018542"}, 
         {"-0.190492949", "-0.0001152"}, 
         {"-0.178015406", "-5.46299E-05"}, 
         {"-0.16396525", "6.9755E-05"}, 
         {"-0.160519775", "0.00010528"}, 
         {"-0.160586223", "0.000148999"}, 
         {"-0.139531908", "0.000239822"}, 
         {"-0.121972289", "0.000330791"}, 
         {"-0.106179706", "0.000468157"}, 
         {"-0.081579012", "0.000671144"}, 
         {"-0.053473777", "0.001066431"}, 
         {"-0.023599045", "0.001630386"}, 
         {"0.01331184", "0.002658159"}, 
         {"0.048492605", "0.00366645"}});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("fununit", new String[]{"mA/cm^2"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("argunit", new String[]{"V"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("ilocmat", "iloc_exp(E_vs_ref_exp)");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .setPropertyInfo("ilocmat", "H. P. Hack, \u201cAtlas of polarization diagrams for naval materials in seawater\u201d, Naval Surface Warfare Centre Technical Report, CARDIVNSWC-TR-61-94/44, 1995.");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").set("E_vs_SHE", "V");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_SHE", "\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_ref_exp_vs_SHE", "0.197 [V]");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_vs_ref_exp", "E_vs_SHE-E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_ref_exp", "\u7535\u6781\u7535\u4f4d vs. \u5b9e\u9a8c Ag/AgCl \u53c2\u6bd4\u7535\u6781");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").addInput("electricpotential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq", "Eeq_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "H. P. Hack, \u201cAtlas of polarization diagrams for naval materials in seawater\u201d, Naval Surface Warfare Centre Technical Report, CARDIVNSWC-TR-61-94/44, 1995.");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "0[mol/m^3]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq_vs_ref_exp", "-0.2 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_ref_exp", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. Ag/AgCl");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("E_ref_exp_vs_SHE", "0.197 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("Eeq_vs_SHE", "Eeq_vs_ref_exp+E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_SHE", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat1").selection().geom("geom1", 2);
    model.component("comp1").material("mat1").selection().named("sel3");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("LocalCurrentDensity", "LocalCurrentDensity", "Local current density");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat2").label("NAB in seawater at 30 C");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").label("Local current density");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .set("funcname", "iloc_exp");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .set("table", new String[][]{{"-1.004778059", "-1.56030719"}, 
         {"-0.87763365", "-1.523362505"}, 
         {"-0.828863572", "-1.486064149"}, 
         {"-0.766152624", "-1.378344134"}, 
         {"-0.726086977", "-1.310880463"}, 
         {"-0.682526543", "-1.15562649"}, 
         {"-0.637212974", "-0.944306285"}, 
         {"-0.590138614", "-0.679941078"}, 
         {"-0.544794423", "-0.453789793"}, 
         {"-0.5011804", "-0.280713444"}, 
         {"-0.455813241", "-0.160958048"}, 
         {"-0.420901735", "-0.095851259"}, 
         {"-0.389467791", "-0.054952001"}, 
         {"-0.363266458", "-0.033137768"}, 
         {"-0.344006852", "-0.016951286"}, 
         {"-0.331736819", "-0.01009216"}, 
         {"-0.324682172", "-0.005639871"}, 
         {"-0.321120399", "-0.003357459"}, 
         {"-0.314077236", "-0.002024244"}, 
         {"-0.307007278", "-0.001022332"}, 
         {"-0.303811059", "0.006819967"}, 
         {"-0.298687541", "0.013335661"}, 
         {"-0.29006158", "0.022978294"}, 
         {"-0.277950401", "0.039096913"}, 
         {"-0.258882185", "0.070871486"}, 
         {"-0.239812055", "0.126854567"}, 
         {"-0.220738097", "0.221386781"}, 
         {"-0.198194232", "0.422157674"}, 
         {"-0.173878094", "0.657496912"}, 
         {"-0.153041431", "0.998407432"}, 
         {"-0.133954076", "1.594747407"}, 
         {"-0.107902027", "2.579897189"}, 
         {"-0.083574405", "3.724383972"}, 
         {"-0.052284004", "5.514769822"}, 
         {"-0.010555175", "8.810796279"}, 
         {"0.041635047", "13.04919726"}});
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .set("fununit", new String[]{"mA/cm^2"});
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").func("int1")
         .set("argunit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .set("ilocmat", "iloc_exp(E_vs_ref_exp)");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .setPropertyInfo("ilocmat", "H. P. Hack, \u201cAtlas of polarization diagrams for naval materials in seawater\u201d, Naval Surface Warfare Centre Technical Report, CARDIVNSWC-TR-61-94/44, 1995.");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").set("E_vs_SHE", "V");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_SHE", "\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .set("E_ref_exp_vs_SHE", "0.197 [V]");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .set("E_vs_ref_exp", "E_vs_SHE-E_ref_exp_vs_SHE");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_ref_exp", "\u7535\u6781\u7535\u4f4d vs. \u5b9e\u9a8c Ag/AgCl \u53c2\u6bd4\u7535\u6781");
    model.component("comp1").material("mat2").propertyGroup("LocalCurrentDensity").addInput("electricpotential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("Eeq", "Eeq_vs_SHE");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "H. P. Hack, \u201cAtlas of polarization diagrams for naval materials in seawater\u201d, Naval Surface Warfare Centre Technical Report, CARDIVNSWC-TR-61-94/44, 1995.");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "0[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("Eeq_vs_ref_exp", "-0.28 [V]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_ref_exp", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. Ag/AgCl");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("E_ref_exp_vs_SHE", "0.197 [V]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("Eeq_vs_SHE", "Eeq_vs_ref_exp+E_ref_exp_vs_SHE");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_SHE", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("uni3");

    model.component("comp1").physics("cp").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cp").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cp").create("refel1", "ReferenceElectrodePoint", 0);
    model.component("comp1").physics("cp").feature("refel1").selection().set(35);
    model.component("comp1").physics("cp").create("imprcs1", "ImpressedCurrentSurface", 2);
    model.component("comp1").physics("cp").feature("imprcs1").selection().named("sel4");
    model.component("comp1").physics("cp").feature("imprcs1").set("Eimpr", "E_control");
    model.component("comp1").physics("cp").feature("imprcs1").set("phisref_src", "root.comp1.cp.phisref_refel1");
    model.component("comp1").physics("cp").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cp").feature("es1").selection().named("sel3");
    model.component("comp1").physics("cp").feature("es1").feature("er1").set("ilocmat_mat", "from_mat");
    model.component("comp1").physics("cp").feature("es1").feature("er1").set("LimitingCurrentDensity", true);
    model.component("comp1").physics("cp").feature("es1").feature("er1").set("ilim", "ilim");
    model.component("comp1").physics("cp").create("passms1", "PassiveMetalSurface", 2);
    model.component("comp1").physics("cp").feature("passms1").selection().named("sel6");
    model.component("comp1").physics("cp").create("tpassms1", "ThinPassiveMetalSurface", 2);
    model.component("comp1").physics("cp").feature("tpassms1").selection().named("sel2");
    model.component("comp1").physics("cp").create("infice1", "InfiniteElectrolyte", 2);
    model.component("comp1").physics("cp").feature("infice1").selection().set(1, 2, 3, 5);
    model.component("comp1").physics("cp").feature("infice1").set("sigmalInf", "sigma");
    model.component("comp1").physics("cp").feature("infice1").set("SymmetryYZPlane", true);
    model.component("comp1").physics("cp").feature("infice1").set("SymmetryXYPlane", true);
    model.component("comp1").physics("cp").feature("init1").set("phil", 0.5);

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 1.5);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmin", 0.01);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().named("uni2");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u6d82\u5c42\u87ba\u65cb\u6868");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cp)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cp)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cp.IlMag");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"abs(cp.itot)"});
    model.result("pg2").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cp)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cp.Evsref"});
    model.result("pg3").create("slit1", "SurfaceSlit");
    model.result("pg3").feature("slit1").set("upexpr", "root.comp1.cp.Evsrefu");
    model.result("pg3").feature("slit1").set("downexpr", "root.comp1.cp.Evsrefd");
    model.result("pg3").feature("slit1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").set("legendpos", "left");
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("sel3");
    model.result("pg2").run();
    model.result("pg2").run();

    model.component("comp1").physics("cp").create("es2", "ElectrodeSurface", 2);
    model.component("comp1").physics("cp").feature("es2").selection().named("sel1");
    model.component("comp1").physics("cp").feature("es2").feature("er1").set("ilocmat_mat", "from_mat");
    model.component("comp1").physics("cp").feature("es2").feature("er1").set("LimitingCurrentDensity", true);
    model.component("comp1").physics("cp").feature("es2").feature("er1").set("ilim", "ilim");
    model.component("comp1").physics("cp").create("tes1", "ThinElectrodeSurface", 2);
    model.component("comp1").physics("cp").feature("tes1").selection().named("sel2");
    model.component("comp1").physics("cp").feature("tes1").feature("er1").set("ilocmat_mat", "from_mat");
    model.component("comp1").physics("cp").feature("tes1").feature("er1").set("LimitingCurrentDensity", true);
    model.component("comp1").physics("cp").feature("tes1").feature("er1").set("ilim", "ilim");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"cp/es2", "cp/tes1"});
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/cp", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u65e0\u6d82\u5c42\u87ba\u65cb\u6868");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u4f4d (cp) 1");
    model.result("pg4").create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cp) 1");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg5").feature("str1").create("col1", "Color");
    model.result("pg5").feature("str1").feature("col1").set("expr", "root.comp1.cp.IlMag");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"abs(cp.itot)"});
    model.result("pg5").feature("surf1").set("inheritplot", "str1");
    model.result("pg5").create("slit1", "SurfaceSlit");
    model.result("pg5").feature("slit1").set("upexpr", "abs(root.comp1.cp.itotu)");
    model.result("pg5").feature("slit1").set("downexpr", "abs(root.comp1.cp.itotd)");
    model.result("pg5").feature("slit1").set("inheritplot", "str1");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cp) 1");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg6").feature("str1").set("posmethod", "start");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cp.Evsref"});
    model.result("pg6").create("slit1", "SurfaceSlit");
    model.result("pg6").feature("slit1").set("upexpr", "root.comp1.cp.Evsrefu");
    model.result("pg6").feature("slit1").set("downexpr", "root.comp1.cp.Evsrefd");
    model.result("pg6").feature("slit1").set("inheritplot", "surf1");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("str1").set("arrowscaleactive", true);
    model.result("pg5").feature("str1").set("arrowscale", 0.5);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().named("uni2");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").set("edges", false);
    model.result("pg6").set("legendpos", "left");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6cbf\u9f99\u9aa8\u7684\u7535\u4f4d");
    model.result("pg7").set("data", "none");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("data", "dset1");
    model.result("pg7").feature("lngr1").selection().set(118, 119);
    model.result("pg7").feature("lngr1").set("expr", "cp.Evsref");
    model.result("pg7").feature("lngr1")
         .set("descr", "\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "y");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "\u6d82\u5c42\u87ba\u65cb\u6868", 0);
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").set("data", "dset2");
    model.result("pg7").feature("lngr2").setIndex("legends", "\u65e0\u6d82\u5c42\u87ba\u65cb\u6868", 0);
    model.result("pg7").run();
    model.result("pg7").set("titletype", "label");
    model.result("pg7").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"cp.Itot_imprcs1"});
    model.result().numerical("gev1").set("descr", new String[]{"\u603b\u5916\u52a0\u7535\u6d41"});
    model.result().numerical("gev1").set("unit", new String[]{"A"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();
    model.result("pg6").run();

    model.title("\u8239\u4f53\u8150\u8680\u9632\u62a4");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u4e8c\u6b21\u7535\u6d41\u5206\u5e03\u6a21\u62df\u4e86\u5728\u8239\u4f53\u5468\u56f4\u4f7f\u7528\u5916\u52a0\u7535\u6d41\u9634\u6781\u4fdd\u62a4 (ICCP) \u7cfb\u7edf\u4fdd\u62a4\u8f74\u548c\u87ba\u65cb\u6868\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("ship_hull.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
