/*
 * viscoplastic_solder_joints_fatigue.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:01 by COMSOL 6.3.0.290. */
public class viscoplastic_solder_joints_fatigue {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Energy_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/te1", true);

    model.param().set("T0", "20[degC]");
    model.param().descr("T0", "Initial temperature");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "power");
    model.func("an1").set("expr", "(flc2hs(x-0.1,0.1)*50)-flc2hs(x-(4.1),0.1)*40");
    model.func("an1").setIndex("argunit", "h", 0);
    model.func("an1").set("fununit", "MW/m^3");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "viscoplastic_solder_joints.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("FR4");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("Copper");
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("Silicon");
    model.component("comp1").selection("sel3").set(3, 24);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("Solder");
    model.component("comp1").selection("sel4").all();
    model.component("comp1").selection("sel4")
         .set(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.component("comp1").selection().duplicate("sel5", "sel4");
    model.component("comp1").selection("sel5").label("Solder_face");
    model.component("comp1").selection("sel5").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel5")
         .set(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("Symmetry Boundaries");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(17, 19, 182, 183);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("Symmetry Complement");
    model.component("comp1").selection("com1").set("entitydim", 2);
    model.component("comp1").selection("com1").set("input", new String[]{"sel6"});

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "T0"}});

    model.component("comp1").physics("solid").feature("lemm1").create("vpl1", "Viscoplasticity", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("vpl1").selection().named("sel4");
    model.component("comp1").physics("solid").feature("lemm1").set("CalculateDissipatedEnergy", true);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("sel6");
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(193);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().named("sel6");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().named("sel3");
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "power(t)");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("com1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 10);
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T0");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").label("FR4 (Circuit Board)");
    model.material("mat1").set("family", "pcb");
    model.material("mat1").set("color", "custom");
    model.material("mat1").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.material("mat1").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.material("mat1").propertyGroup("Enu").set("E", "22[GPa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.15");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.material("mat2").label("Copper");
    model.material("mat2").set("family", "copper");
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.material().create("mat3", "Common", "");
    model.material("mat3").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat3").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat3").label("Silicon");
    model.material("mat3").set("family", "custom");
    model.material("mat3").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.material("mat3").set("diffuse", "custom");
    model.material("mat3")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.material("mat3").set("ambient", "custom");
    model.material("mat3")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.material("mat3").set("noise", true);
    model.material("mat3").set("fresnel", 0.7);
    model.material("mat3").set("roughness", 0.5);
    model.material("mat3").set("diffusewrap", 0);
    model.material("mat3").set("reflectance", 0);
    model.material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.material("mat3").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.material("mat3").propertyGroup("Enu").set("E", "170[GPa]");
    model.material("mat3").propertyGroup("Enu").set("nu", "0.28");
    model.material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});
    model.material().create("mat4", "Common", "");
    model.material("mat4").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat4").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.material("mat4").propertyGroup().create("Anand", "Anand", "Anand viscoplasticity");
    model.material("mat4").label("Solder, 60Sn-40Pb");
    model.material("mat4").set("family", "custom");
    model.material("mat4").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.material("mat4").set("diffuse", "custom");
    model.material("mat4")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat4").set("ambient", "custom");
    model.material("mat4")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat4").set("noise", true);
    model.material("mat4").set("fresnel", 0.9);
    model.material("mat4").set("roughness", 0.1);
    model.material("mat4").set("diffusewrap", 0);
    model.material("mat4").set("reflectance", 0);
    model.material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"6.67e6[S/m]", "0", "0", "0", "6.67e6[S/m]", "0", "0", "0", "6.67e6[S/m]"});
    model.material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"21e-6[1/K]", "0", "0", "0", "21e-6[1/K]", "0", "0", "0", "21e-6[1/K]"});
    model.material("mat4").propertyGroup("def").set("heatcapacity", "150[J/(kg*K)]");
    model.material("mat4").propertyGroup("def").set("density", "9000[kg/m^3]");
    model.material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"50[W/(m*K)]", "0", "0", "0", "50[W/(m*K)]", "0", "0", "0", "50[W/(m*K)]"});
    model.material("mat4").propertyGroup("Enu").set("E", "10[GPa]");
    model.material("mat4").propertyGroup("Enu").set("nu", "0.4");
    model.material("mat4").propertyGroup("linzRes").set("rho0", "4.99e-7[ohm*m]");
    model.material("mat4").propertyGroup("linzRes").addInput("temperature");
    model.material("mat4").propertyGroup("Anand").set("A_ana", "1.49e7[1/s]");
    model.material("mat4").propertyGroup("Anand").set("Q_ana", "90046[J/mol]");
    model.material("mat4").propertyGroup("Anand").set("xi_ana", "11");
    model.material("mat4").propertyGroup("Anand").set("m_ana", "0.303");
    model.material("mat4").propertyGroup("Anand").set("ssat_ana", "80.42[MPa]");
    model.material("mat4").propertyGroup("Anand").set("sa_init", "56.33[MPa]");
    model.material("mat4").propertyGroup("Anand").set("h0_ana", "2640.75[MPa]");
    model.material("mat4").propertyGroup("Anand").set("a_ana", "1.34");
    model.material("mat4").propertyGroup("Anand").set("n_ana", "0.0231");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").selection().named("sel1");
    model.component("comp1").material("matlnk1").set("family", "pcb");

    model.component("comp1").view("view1").set("showmaterial", true);

    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().named("sel2");
    model.component("comp1").material("matlnk2").set("link", "mat2");
    model.component("comp1").material("matlnk2").set("family", "copper");
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").selection().named("sel3");
    model.component("comp1").material("matlnk3").set("link", "mat3");
    model.component("comp1").material("matlnk3").set("color", "black");
    model.component("comp1").material().create("matlnk4", "Link");
    model.component("comp1").material("matlnk4").selection().named("sel4");
    model.component("comp1").material("matlnk4").set("link", "mat4");
    model.component("comp1").material("matlnk4").set("family", "steel");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(7);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time")
         .set("tlist", "0 0.005 range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)}");
    model.study("std1").feature("time").setSolveFor("/physics/solid", false);
    model.study("std1").create("time2", "Transient");
    model.study("std1").feature("time2").set("tunit", "h");
    model.study("std1").feature("time2")
         .set("tlist", "0 0.005 range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)}");
    model.study("std1").feature("time2").setSolveFor("/physics/ht", false);
    model.study("std1").feature("time2").set("usesol", true);
    model.study("std1").feature("time2").set("notsolmethod", "sol");
    model.study("std1").feature("time2").set("notstudy", "std1");
    model.study("std1").feature("time2").set("notsolnum", "all");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("storeudot", false);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("v2").feature("comp1_solid_Wvp").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_solid_Wvp").set("scaleval", "1e5");
    model.sol("sol1").feature("t2").set("storeudot", false);
    model.sol("sol1").feature("t2").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t2").set("estrat", "exclude");
    model.sol("sol1").feature("t2").create("se1", "Segregated");
    model.sol("sol1").feature("t2").feature("se1").set("segterm", "iter");
    model.sol("sol1").feature("t2").feature("se1").feature("ssDef").label("Displacement Field");
    model.sol("sol1").feature("t2").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_T", "comp1_u"});
    model.sol("sol1").feature("t2").feature("se1").feature("ssDef").set("subtermconst", "tol");
    model.sol("sol1").feature("t2").feature("se1").feature("ssDef").set("subntolfact", 1);
    model.sol("sol1").feature("t2").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("t2").feature("se1").feature("ss1").label("Energy Dissipation");
    model.sol("sol1").feature("t2").feature("se1").feature("ss1").set("subtermconst", "tol");
    model.sol("sol1").feature("t2").feature("se1").feature("ss1").set("subntolfact", 1);
    model.sol("sol1").feature("t2").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_solid_Wvp"});

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 63, 0);
    model.result("pg1").label("Stress (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Temperature (ht)");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "T");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 24, 0);
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("unit", "degC");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("Deformation Resistance History");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(36);
    model.result("pg3").feature("ptgr1").set("expr", "solid.saGp");
    model.result("pg3").feature("ptgr1").set("unit", "MPa");
    model.result("pg3").feature("ptgr1").set("linewidth", 2);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("Strain History");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(36);
    model.result("pg4").feature("ptgr1").set("expr", "solid.gpeval(solid.el13)");
    model.result("pg4").feature("ptgr1").set("linewidth", 2);
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "Total strain", 0);
    model.result("pg4").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").set("expr", "solid.gpeval(solid.evpl13)");
    model.result("pg4").feature("ptgr2").setIndex("legends", "Viscoplastic strain", 0);
    model.result("pg4").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr3").set("expr", "solid.gpeval(solid.el13-solid.evpl13)");
    model.result("pg4").feature("ptgr3").setIndex("legends", "Elastic strain", 0);
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Shear strain, xz-component");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("legendlayout", "outside");
    model.result("pg4").set("legendposoutside", "bottom");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("Dissipation History");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("expr", "solid.WvpGp");
    model.result("pg5").feature("ptgr1").set("descr", "Viscoplastic dissipation density");
    model.result("pg5").feature("ptgr1").set("unit", "kJ/m^3");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("Temperature History");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("expr", "T");
    model.result("pg6").feature("ptgr1").set("descr", "Temperature");
    model.result("pg6").feature("ptgr1").set("unit", "degC");
    model.result("pg6").run();
    model.result("pg5").run();

    model.title("\u710a\u70b9\u7684\u9ecf\u5851\u6027\u8815\u53d8");

    model
         .description("\u672c\u4f8b\u901a\u8fc7 Anand \u9ecf\u5851\u6027\u6a21\u578b\u7814\u7a76\u4e86\u710a\u70b9\u53d7\u70ed\u8f7d\u8377\u4f5c\u7528\u65f6\u7684\u9ecf\u5851\u6027\u8815\u53d8\u3002");

    model.label("viscoplastic_solder_joints.mph");

    model.result("pg5").run();

    model.component("comp1").label("\u5b8c\u6574\u6a21\u578b");

    model.param().set("lcrack", "2.6457e-4[in]");
    model.param().descr("lcrack", "\u88c2\u7eb9\u5c3a\u5bf8");

    model.func("an1")
         .set("expr", "(flc2hs(x-0.1,0.1)*50)*(x<6)-flc2hs(mod(x,6)-4.1,0.1)*40+(flc2hs(mod(x,6)-0.1,0.1)*40+10)*(x>=6)");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().all();

    model.study("std1").label("\u5b8c\u6574\u6a21\u578b\uff1a\u8f7d\u8377\u5386\u7a0b");
    model.study("std1").feature("time")
         .set("tlist", "0 0.005 range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)} 6+{range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)}} 12+{range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)}} 18+{range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)}}");
    model.study("std1").feature("time2")
         .set("tlist", "0 0.005 range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)} 6+{range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)}} 12+{range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)}} 18+{range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)}}");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ftg", false);
    model.study("std1").feature("time2").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").create("ener1", "EnergyBasedModel", 3);
    model.component("comp1").physics("ftg").feature("ener1").selection().named("sel4");
    model.component("comp1").physics("ftg").feature("ener1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics("ftg").feature("ener1").set("ftgEnerCriterion", "Darveaux");
    model.component("comp1").physics("ftg").feature("ener1").set("ftgEnerType", "Viscoplasticity");
    model.component("comp1").physics("ftg").feature("ener1").set("a", "lcrack");

    model.material("mat4").propertyGroup()
         .create("fatigueEnergyDarveaux", "fatigueEnergyDarveaux", "Darveaux[Fatigue]");
    model.material("mat4").propertyGroup("fatigueEnergyDarveaux").set("K1_Darveaux", new String[]{"13173"});
    model.material("mat4").propertyGroup("fatigueEnergyDarveaux").set("k2_Darveaux", new String[]{"-1.45"});
    model.material("mat4").propertyGroup("fatigueEnergyDarveaux").set("K3_Darveaux", new String[]{"3.92e-7[in]"});
    model.material("mat4").propertyGroup("fatigueEnergyDarveaux").set("k4_Darveaux", new String[]{"1.12"});
    model.material("mat4").propertyGroup("fatigueEnergyDarveaux").set("Wref_Darveaux", new String[]{"1[psi]"});

    model.study().create("std2");
    model.study("std2").create("ftge", "Fatigue");
    model.study("std2").feature("ftge").set("ftplistmethod", "manual");
    model.study("std2").feature("ftge").set("solnum", "auto");
    model.study("std2").feature("ftge").set("usesol", "off");
    model.study("std2").feature("ftge").set("outputmap", new String[]{});
    model.study("std2").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ht", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("ftge").setSolveFor("/multiphysics/te1", true);
    model.study("std2").label("\u5b8c\u6574\u6a21\u578b\uff1a\u75b2\u52b3\u8bc4\u4f30");
    model.study("std2").feature("ftge").set("usesol", true);
    model.study("std2").feature("ftge").set("notsolmethod", "sol");
    model.study("std2").feature("ftge").set("notstudy", "std1");
    model.study("std2").feature("ftge").set("notsolnum", "from_list");
    model.study("std2").feature("ftge")
         .set("notlistsolnum", new int[]{185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246});
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"ftg.ctf"});
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("colortabletrans", "none");
    model.result("pg7").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf1").set("colortablerev", true);
    model.result("pg7").feature("surf1").set("colortable", "Traffic");
    model.result("pg7").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg)");
    model.result("pg7").feature("surf1").create("mrkr1", "Marker");
    model.result("pg7").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg7").feature("surf1").feature("mrkr1").set("display", "min");
    model.result("pg7").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").label("\u5b50\u6a21\u578b");

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("filename", "viscoplastic_solder_joints.mphbin");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl1").set("r", "2.4e-4");
    model.component("comp2").geom("geom2").feature("cyl1").set("h", "9e-4");
    model.component("comp2").geom("geom2").feature("cyl1").set("pos", new String[]{"68.28e-4", "55.38e-4", "0"});
    model.component("comp2").geom("geom2").feature("cyl1").setIndex("pos", "10e-4", 2);
    model.component("comp2").geom("geom2").run("cyl1");
    model.component("comp2").geom("geom2").create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("uni1").selection("input").set("imp1");
    model.component("comp2").geom("geom2").run("uni1");
    model.component("comp2").geom("geom2").create("int1", "Intersection");
    model.component("comp2").geom("geom2").feature("int1").selection("input").set("cyl1", "uni1");
    model.component("comp2").geom("geom2").run("int1");
    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").set("quickz", "13.5e-4");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").geom("geom2").feature().duplicate("wp2", "wp1");
    model.component("comp2").geom("geom2").feature("wp2").set("quickz", "15.5e-4");
    model.component("comp2").geom("geom2").run("wp2");
    model.component("comp2").geom("geom2").create("par1", "Partition");
    model.component("comp2").geom("geom2").feature("par1").set("partitionwith", "workplane");
    model.component("comp2").geom("geom2").feature("par1").set("workplane", "wp1");
    model.component("comp2").geom("geom2").feature("par1").set("repairtoltype", "relative");
    model.component("comp2").geom("geom2").feature("par1").set("repairtol", "3E-4");
    model.component("comp2").geom("geom2").feature("par1").selection("input").set("int1");
    model.component("comp2").geom("geom2").run("par1");
    model.component("comp2").geom("geom2").create("par2", "Partition");
    model.component("comp2").geom("geom2").feature("par2").selection("input").set("par1");
    model.component("comp2").geom("geom2").feature("par2").set("partitionwith", "workplane");
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/solid2", false);
    model.study("std1").feature("time2").setSolveFor("/physics/solid2", false);

    model.component("comp2").physics("solid2").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp2").physics().create("ht2", "HeatTransfer", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/ht2", false);
    model.study("std1").feature("time2").setSolveFor("/physics/ht2", false);

    model.component("comp2").multiphysics().create("te2", "ThermalExpansion", 3);

    model.study("std1").feature("time").setSolveFor("/multiphysics/te2", false);
    model.study("std1").feature("time2").setSolveFor("/multiphysics/te2", false);
    model.study("std2").feature("ftge").setSolveFor("/multiphysics/te2", false);

    model.component("comp2").multiphysics("te2").set("Heat_physics", "ht2");
    model.component("comp2").multiphysics("te2").set("Solid_physics", "solid2");
    model.component("comp2").multiphysics("te2").selection().all();

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("solid2").feature("lemm1").set("CalculateDissipatedEnergy", true);
    model.component("comp2").physics("solid2").feature("lemm1").create("vpl1", "Viscoplasticity", 3);
    model.component("comp2").physics("solid2").feature("lemm1").feature("vpl1").selection().set(4, 5, 6);
    model.component("comp2").physics("solid2").create("disp1", "Displacement2", 2);
    model.component("comp2").physics("solid2").feature("disp1").selection()
         .set(1, 2, 3, 4, 5, 8, 9, 11, 22, 23, 24, 25, 26, 27);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("U0", "comp1.genext1(comp1.u)", 0);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("U0", "comp1.genext1(comp1.v)", 1);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("U0", "comp1.genext1(comp1.w)", 2);
    model.component("comp2").physics("solid2").feature("disp1").set("constraintType", "unidirectionalVar");
    model.component("comp2").physics("ht2").create("hs1", "HeatSource", 3);
    model.component("comp2").physics("ht2").feature("hs1").selection().set(3);
    model.component("comp2").physics("ht2").feature("hs1").set("Q0", "power(t)");
    model.component("comp2").physics("ht2").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp2").physics("ht2").feature("hf1").selection().set(7, 10, 12, 13, 14, 15, 17, 18);
    model.component("comp2").physics("ht2").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp2").physics("ht2").feature("hf1").set("h", 10);
    model.component("comp2").physics("ht2").feature("hf1").set("Text", "T0");
    model.component("comp2").physics("ht2").create("temp1", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht2").feature("temp1").selection()
         .set(1, 2, 3, 4, 5, 8, 9, 11, 22, 23, 24, 25, 26, 27);
    model.component("comp2").physics("ht2").feature("temp1").set("T0", "comp1.genext1(comp1.T)");

    model.component("comp2").material().create("matlnk5", "Link");
    model.component("comp2").material("matlnk5").selection().set(1);
    model.component("comp2").material().create("matlnk6", "Link");
    model.component("comp2").material("matlnk6").selection().set(2);
    model.component("comp2").material("matlnk6").set("link", "mat2");
    model.component("comp2").material().create("matlnk7", "Link");
    model.component("comp2").material("matlnk7").selection().set(3);
    model.component("comp2").material("matlnk7").set("link", "mat3");
    model.component("comp2").material().create("matlnk8", "Link");
    model.component("comp2").material("matlnk8").selection().set(4, 5, 6);
    model.component("comp2").material("matlnk8").set("link", "mat4");

    model.component("comp2").mesh("mesh2").automatic(false);
    model.component("comp2").mesh("mesh2").feature("size1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("size1").selection().set(4, 5, 6);
    model.component("comp2").mesh("mesh2").feature("size1").set("custom", false);
    model.component("comp2").mesh("mesh2").feature("size1").set("hauto", 4);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/solid", false);
    model.study("std3").feature("time").setSolveFor("/physics/ht", false);
    model.study("std3").feature("time").setSolveFor("/physics/ftg", false);
    model.study("std3").feature("time").setSolveFor("/physics/solid2", true);
    model.study("std3").feature("time").setSolveFor("/physics/ht2", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/te1", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/te2", true);
    model.study("std3").label("\u5b50\u6a21\u578b\uff1a\u8f7d\u8377\u5386\u7a0b");
    model.study("std3").feature("time").set("tunit", "h");
    model.study("std3").feature("time")
         .set("tlist", "0 range(1,1,17) 18+{range(0,0.025,0.5) range(0.75,0.25,3.75) 3.95 4+{range(0.025,0.025,0.5) range(0.75,0.25,2)}}");
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std1");
    model.study("std3").feature("time").set("notsolnum", "all");
    model.study("std3").showAutoSequences("all");

    model.sol("sol4").feature("v1").feature("comp2_solid2_Wvp").set("scalemethod", "manual");
    model.sol("sol4").feature("v1").feature("comp2_solid2_Wvp").set("scaleval", "1e5");
    model.sol("sol4").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol4").feature("t1").set("storeudot", false);
    model.sol("sol4").feature("t1").feature("se1").set("segterm", "iter");
    model.sol("sol4").feature("t1").feature("se1").feature("ss1").set("subtermconst", "tol");
    model.sol("sol4").feature("t1").feature("se1").feature("ss1").set("subntolfact", 1);
    model.sol("sol4").feature("t1").feature("se1").feature("ss2").set("subdtech", "const");
    model.sol("sol4").feature("t1").feature("se1").feature("ss2").set("subtermconst", "tol");
    model.sol("sol4").feature("t1").feature("se1").feature("ss2").set("segvar", new String[]{"comp2_u2"});
    model.sol("sol4").feature("t1").feature("se1").create("ss3", "SegregatedStep");
    model.sol("sol4").feature("t1").feature("se1").feature().move("ss3", 2);
    model.sol("sol4").feature("t1").feature("se1").feature("ss3").set("segvar", new String[]{"comp2_solid2_Wvp"});
    model.sol("sol4").feature("t1").feature("se1").feature("ss3").label("\u80fd\u8017");
    model.sol("sol4").feature("t1").feature("se1").feature("ss3").set("subtermconst", "tol");
    model.sol("sol4").feature("t1").feature("se1").feature("ss3").set("subntolfact", 1);

    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset5");
    model.result("pg8").setIndex("looplevel", 79, 0);
    model.result("pg8").label("\u5e94\u529b (solid2)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg8").feature("vol1").set("threshold", "manual");
    model.result("pg8").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("vol1").set("colortable", "Rainbow");
    model.result("pg8").feature("vol1").set("colortabletrans", "none");
    model.result("pg8").feature("vol1").set("colorscalemode", "linear");
    model.result("pg8").feature("vol1").set("resolution", "custom");
    model.result("pg8").feature("vol1").set("refine", 2);
    model.result("pg8").feature("vol1").set("colortable", "Prism");
    model.result("pg8").feature("vol1").create("def", "Deform");
    model.result("pg8").feature("vol1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg8").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u6e29\u5ea6 (ht2)");
    model.result("pg9").set("data", "dset5");
    model.result("pg9").setIndex("looplevel", 79, 0);
    model.result("pg9").feature().create("vol1", "Volume");
    model.result("pg9").feature("vol1").set("showsolutionparams", "on");
    model.result("pg9").feature("vol1").set("solutionparams", "parent");
    model.result("pg9").feature("vol1").set("expr", "T2");
    model.result("pg9").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg9").feature("vol1").set("smooth", "internal");
    model.result("pg9").feature("vol1").set("showsolutionparams", "on");
    model.result("pg9").feature("vol1").set("data", "parent");
    model.result("pg8").run();

    model.component("comp2").physics().create("ftg2", "Fatigue", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/ftg2", false);
    model.study("std1").feature("time2").setSolveFor("/physics/ftg2", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg2", false);
    model.study("std3").feature("time").setSolveFor("/physics/ftg2", false);

    model.component("comp2").physics("ftg2").create("ener1", "EnergyBasedModel", 3);
    model.component("comp2").physics("ftg2").feature("ener1").selection().set(4, 5, 6);
    model.component("comp2").physics("ftg2").feature("ener1").set("fatigueInputPhysics", "solid2");
    model.component("comp2").physics("ftg2").feature("ener1").set("ftgEnerCriterion", "Darveaux");
    model.component("comp2").physics("ftg2").feature("ener1").set("ftgEnerType", "Viscoplasticity");
    model.component("comp2").physics("ftg2").feature("ener1").set("ftgEnerVolAverMethod", "EntireSelection");
    model.component("comp2").physics("ftg2").feature("ener1").set("a", "lcrack");
    model.component("comp2").physics().create("ftg3", "Fatigue", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/ftg3", false);
    model.study("std1").feature("time2").setSolveFor("/physics/ftg3", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg3", false);
    model.study("std3").feature("time").setSolveFor("/physics/ftg3", false);

    model.component("comp2").physics("ftg3").create("ener1", "EnergyBasedModel", 3);
    model.component("comp2").physics("ftg3").feature("ener1").selection().set(4, 5, 6);
    model.component("comp2").physics("ftg3").feature("ener1").set("fatigueInputPhysics", "solid2");
    model.component("comp2").physics("ftg3").feature("ener1").set("ftgEnerCriterion", "Darveaux");
    model.component("comp2").physics("ftg3").feature("ener1").set("ftgEnerType", "Viscoplasticity");
    model.component("comp2").physics("ftg3").feature("ener1").set("a", "lcrack");

    model.study().create("std4");
    model.study("std4").create("ftge", "Fatigue");
    model.study("std4").feature("ftge").set("ftplistmethod", "manual");
    model.study("std4").feature("ftge").set("solnum", "auto");
    model.study("std4").feature("ftge").set("usesol", "off");
    model.study("std4").feature("ftge").set("outputmap", new String[]{});
    model.study("std4").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std4").feature("ftge").setSolveFor("/physics/ht", false);
    model.study("std4").feature("ftge").setSolveFor("/physics/ftg", false);
    model.study("std4").feature("ftge").setSolveFor("/physics/solid2", false);
    model.study("std4").feature("ftge").setSolveFor("/physics/ht2", false);
    model.study("std4").feature("ftge").setSolveFor("/physics/ftg2", true);
    model.study("std4").feature("ftge").setSolveFor("/physics/ftg3", true);
    model.study("std4").feature("ftge").setSolveFor("/multiphysics/te1", true);
    model.study("std4").feature("ftge").setSolveFor("/multiphysics/te2", true);
    model.study("std4").label("\u5b50\u6a21\u578b\uff1a\u75b2\u52b3\u8bc4\u4f30");
    model.study("std4").feature("ftge").set("usesol", true);
    model.study("std4").feature("ftge").set("notsolmethod", "sol");
    model.study("std4").feature("ftge").set("notstudy", "std3");
    model.study("std4").feature("ftge").set("notsolnum", "from_list");
    model.study("std4").feature("ftge")
         .set("notlistsolnum", new int[]{19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79});
    model.study("std4").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset7");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"ftg2.ctf"});
    model.result("pg10").feature("surf1").set("colortable", "Rainbow");
    model.result("pg10").feature("surf1").set("colortabletrans", "none");
    model.result("pg10").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg10").feature("surf1").set("colortablerev", true);
    model.result("pg10").feature("surf1").set("colortable", "Traffic");
    model.result("pg10").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg2)");
    model.result("pg10").feature("surf1").create("mrkr1", "Marker");
    model.result("pg10").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg10").feature("surf1").feature("mrkr1").set("display", "min");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset7");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"ftg3.ctf"});
    model.result("pg11").feature("surf1").set("colortable", "Rainbow");
    model.result("pg11").feature("surf1").set("colortabletrans", "none");
    model.result("pg11").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg11").feature("surf1").set("colortablerev", true);
    model.result("pg11").feature("surf1").set("colortable", "Traffic");
    model.result("pg11").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg3)");
    model.result("pg11").feature("surf1").create("mrkr1", "Marker");
    model.result("pg11").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg11").feature("surf1").feature("mrkr1").set("display", "min");
    model.result("pg10").run();
    model.result("pg9").run();
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u5b8c\u6574\u6a21\u578b", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("expr", "solid2.WvpGp");
    model.result("pg5").feature("ptgr2").set("unit", "kJ/m^3");
    model.result("pg5").feature("ptgr2").set("data", "dset5");
    model.result("pg5").feature("ptgr2").selection().set(9);
    model.result("pg5").feature("ptgr2").set("linemarker", "circle");
    model.result("pg5").feature("ptgr2").setIndex("legends", "\u5b50\u6a21\u578b", 0);
    model.result("pg5").run();
    model.result().duplicate("pg12", "pg5");
    model.result("pg12").run();
    model.result("pg12").label("\u526a\u5207\u9ecf\u5851\u6027\u5386\u53f2");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u526a\u5207\u9ecf\u5851\u6027");
    model.result("pg12").set("legendpos", "lowerleft");
    model.result("pg12").run();
    model.result("pg12").feature("ptgr1").set("expr", "solid.evplGp13");
    model.result("pg12").feature("ptgr1")
         .set("descr", "\u9ecf\u5851\u6027\u5e94\u53d8\u5f20\u91cf\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c13 \u5206\u91cf");
    model.result("pg12").run();
    model.result("pg12").feature("ptgr2").set("expr", "solid2.evplGp13");
    model.result("pg12").feature("ptgr2")
         .set("descr", "\u9ecf\u5851\u6027\u5e94\u53d8\u5f20\u91cf\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c13 \u5206\u91cf");
    model.result("pg12").run();
    model.result("pg6").run();
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u6e29\u5ea6 (C)");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg6").feature("ptgr1").setIndex("legends", "\u5b8c\u6574\u6a21\u578b", 0);
    model.result("pg6").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").set("expr", "T2");
    model.result("pg6").feature("ptgr2").set("unit", "degC");
    model.result("pg6").feature("ptgr2").set("data", "dset5");
    model.result("pg6").feature("ptgr2").selection().set(9);
    model.result("pg6").feature("ptgr2").set("linestyle", "none");
    model.result("pg6").feature("ptgr2").set("linemarker", "circle");
    model.result("pg6").feature("ptgr2").setIndex("legends", "\u5b50\u6a21\u578b", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("mrkr1").set("precision", 2);
    model.result("pg9").run();
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature("surf1").feature("mrkr1").set("precision", 1);
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("min1", "MinVolume");
    model.result().evaluationGroup("eg1").feature("min1").set("data", "dset3");
    model.result().evaluationGroup("eg1").feature("min1").selection().named("sel4");
    model.result().evaluationGroup("eg1").feature("min1").set("expr", new String[]{"ftg.ctf"});
    model.result().evaluationGroup("eg1").feature("min1")
         .set("descr", new String[]{"\u5931\u6548\u5faa\u73af\u6b21\u6570"});
    model.result().evaluationGroup("eg1").feature("min1").set("unit", new String[]{"1"});
    model.result().evaluationGroup("eg1").create("min2", "MinVolume");
    model.result().evaluationGroup("eg1").feature("min2").set("data", "dset7");
    model.result().evaluationGroup("eg1").feature("min2").selection().set(4, 5, 6);
    model.result().evaluationGroup("eg1").feature("min2").setIndex("expr", "ftg2.ctf", 0);
    model.result().evaluationGroup("eg1").feature("min2")
         .setIndex("descr", "\u5931\u6548\u5faa\u73af\u6b21\u6570\uff0c\u5b50\u6a21\u578b", 0);
    model.result().evaluationGroup("eg1").feature("min2").setIndex("expr", "ftg3.ctf", 1);
    model.result().evaluationGroup("eg1").feature("min2")
         .setIndex("descr", "\u5931\u6548\u5faa\u73af\u6b21\u6570\uff0c\u5b50\u6a21\u578b\u4e2d\u7684\u8584\u5c42", 1);
    model.result().evaluationGroup("eg1").run();
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("mrkr1").active(false);
    model.result("pg7").feature("surf1").feature("mrkr1").active(true);
    model.result("pg11").run();
    model.result("pg10").run();
    model.result("pg7").run();

    model.title("\u7403\u6805\u9635\u5217\u4e2d\u57fa\u4e8e\u80fd\u91cf\u7684\u70ed\u75b2\u52b3\u9884\u6d4b");

    model
         .description("\u672c\u4f8b\u5206\u6790\u70ed\u8f7d\u8377\u4e0b\u9ecf\u5851\u6027\u710a\u70b9\u7684\u75b2\u52b3\u53ef\u9760\u6027\u3002\u57fa\u4e8e Darveaux \u80fd\u91cf\u6a21\u578b\u9884\u6d4b\u4e86\u4e24\u4e2a\u7403\u6805\u88c5\u914d\u7684\u710a\u70b9\u5bff\u547d\u3002\u6a21\u578b\u57fa\u4e8e\u53ef\u80fd\u5b58\u5728\u710a\u70b9\u88c2\u7eb9\u8584\u5c42\u4e2d\u7684\u5e73\u5747\u80fd\u8017\u5bc6\u5ea6\u8ba1\u7b97\u4e86\u710a\u70b9\u7684\u7834\u574f\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("viscoplastic_solder_joints_fatigue.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
