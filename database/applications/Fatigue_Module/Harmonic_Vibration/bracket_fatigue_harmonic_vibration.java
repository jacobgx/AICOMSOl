/*
 * bracket_fatigue_harmonic_vibration.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:01 by COMSOL 6.3.0.290. */
public class bracket_fatigue_harmonic_vibration {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Harmonic_Vibration");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("Fh", "800[N]");
    model.param().descr("Fh", "Force per hole");
    model.param().set("dHole", "50[mm]");
    model.param().descr("dHole", "Hole diameter");
    model.param().set("tArm", "8[mm]");
    model.param().descr("tArm", "Thickness of arm");
    model.param().set("p0", "Fh/(dHole*tArm*pi/4)");
    model.param().descr("p0", "Peak load intensity");
    model.param()
         .comments("The expression for the peak load intensity is obtained by integrating the projection of the pressure distribution in the hole into a total force.\n\n<eqv>F</eqv><sub>h</sub> = <symbol>\u00f2</symbol><eqv>t</eqv> <eqv>p</eqv><sub>0</sub> cos<sup>2</sup> \\philetter <eqv>R</eqv> d\\philetter = \\pi /2 <eqv>p</eqv><sub>0</sub> <eqv>t R </eqv>\n\nHere, <eqv>R</eqv> is the hole radius, and <eqv>t</eqv> is the thickness of the arm. The integral is taken from -\\pi /2 to \\pi /2.");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "bracket.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Partition Block");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.025, 0.13, 0.04});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-0.11, -0.12, 0.025});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("mir2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").named("csel1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "load");
    model.component("comp1").func("an1").set("expr", "F*cos(atan2(py,abs(px)))");
    model.component("comp1").func("an1").set("args", "F, py, px");
    model.component("comp1").func("an1").setIndex("argunit", "Pa", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").setIndex("argunit", "m", 2);
    model.component("comp1").func("an1").set("fununit", "Pa");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("Bolt 1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(41);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("Bolt 2");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(43);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("Bolt 3");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(55);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("Bolt 4");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(57);
    model.component("comp1").selection("sel4").set("groupcontang", true);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("Bolt Holes");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("color", "8");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2", "sel3", "sel4"});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("Left Pin Hole");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(4);
    model.component("comp1").selection("sel5").set("groupcontang", true);
    model.component("comp1").selection("sel5").set("color", "9");
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("Right Pin Hole");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(75);
    model.component("comp1").selection("sel6").set("groupcontang", true);
    model.component("comp1").selection("sel6").set("color", "12");
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("Pin Holes");
    model.component("comp1").selection("uni2").set("entitydim", 2);
    model.component("comp1").selection("uni2").set("input", new String[]{"sel5", "sel6"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("Bolt Hole Edges");
    model.component("comp1").selection("adj1").set("entitydim", 2);
    model.component("comp1").selection("adj1").set("outputdim", 1);
    model.component("comp1").selection("adj1").set("input", new String[]{"uni1"});

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").label("Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").label("Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").label("Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("uni1");

    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("cm1", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm1").selection("ent").set("par1", 2, 5);
    model.component("comp1").geom("geom1").run("cm1");
    model.component("comp1").geom("geom1").feature("cm1").setIndex("parname", "PinHoleX", 0);
    model.component("comp1").geom("geom1").feature("cm1").setIndex("parname", "PinHoleY", 1);
    model.component("comp1").geom("geom1").feature("cm1").setIndex("parname", "PinHoleZ", 2);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("uni2");
    model.component("comp1").physics("solid").feature("bndl1").set("coordinateSystem", "sys1");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "load(-p0,Y-PinHoleY,Z)*(X*Z>0)"});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("adj1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 4, 5, 6, 9);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(1, 33, 37, 50, 72);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "6[mm]");
    model.component("comp1").mesh("mesh1").run("swe1");

    model.param().set("elSize", "6[mm]", "Element size");

    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "elSize");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "elSize");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(24, 28, 63, 70);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
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
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("Boundary Loads (solid)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg2").feature("surf1").label("Gray Surfaces");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").set("inheritcolor", false);
    model.result("pg2").feature("surf1").set("inheritrange", false);
    model.result("pg2").feature("surf1").set("inherittransparency", false);
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "Displacement field");
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", 0);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88);
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1")
         .set("expr", new String[]{"solid.bndl1.fax", "solid.bndl1.fay", "solid.bndl1.faz"});
    model.result("pg2").feature("arws1").set("placement", "gausspoints");
    model.result("pg2").feature("arws1").set("arrowbase", "tail");
    model.result("pg2").feature("arws1").label("Boundary Load 1");
    model.result("pg2").feature("arws1").set("inheritplot", "none");
    model.result("pg2").feature("arws1").create("col", "Color");
    model.result("pg2").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg2").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg2").feature("arws1").set("color", "red");
    model.result("pg2").feature("arws1").create("def", "Deform");
    model.result("pg2").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws1").feature("def").set("descr", "Displacement field");
    model.result("pg2").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws1").feature("def").set("scale", 0);
    model.result("pg2").feature().move("surf1", 1);
    model.result("pg2").label("Boundary Loads (solid)");
    model.result("pg2").run();
    model.result("pg2").run();

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").view("view1").set("ssao", true);

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "Displacement", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "Stress tensor", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("rangecoloractive", true);
    model.result("pg1").feature("vol1").set("rangecolormax", 70);
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").feature().copy("arws1", "pg2/arws1");
    model.result("pg1").run();
    model.result("pg1").feature("arws1").set("arrowbase", "head");
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", "1E-8");
    model.result("pg1").feature("arws1").set("inheritplot", "vol1");
    model.result("pg1").feature("arws1").set("inheritarrowscale", false);
    model.result("pg1").feature("arws1").set("inheritcolor", false);
    model.result("pg1").feature("arws1").set("inheritrange", false);
    model.result("pg1").run();
    model.result("pg1").feature("arws1").feature("col").set("colorlegend", false);

    model.component("comp1").view("view1").set("showgrid", false);

    return model;
  }

  public static Model run2(Model model) {

    model.result("pg1").run();
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").set("descriptionintitle", false);
    model.result("pg1").set("unitintitle", false);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("Displacement (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result("pg3").label("Displacement (solid)");
    model.result("pg3").run();
    model.result("pg3").label("Total Displacement");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").create("mrkr1", "Marker");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("mrkr1").set("display", "max");
    model.result("pg3").feature("vol1").feature("mrkr1").set("backgroundcolor", "fromtheme");
    model.result("pg3").feature("vol1").feature("mrkr1").set("precision", 2);
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "bottom");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("Principal Stress");
    model.result("pg4").selection().geom("geom1", 3);
    model.result("pg4").selection().geom("geom1", 3);
    model.result("pg4").selection().set(1, 2, 3, 4);
    model.result("pg4").set("applyselectiontodatasetedges", true);
    model.result("pg4").create("priv1", "PrincipalVolume");
    model.result("pg4").feature("priv1").set("xnumber", 20);
    model.result("pg4").feature("priv1").set("ynumber", 40);
    model.result("pg4").feature("priv1").set("znumber", 10);
    model.result("pg4").feature("priv1").set("arrowlength", "logarithmic");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("Stress Along Fillet");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(48);
    model.result("pg5").feature("lngr1").set("expr", "solid.sp1Gp");
    model.result("pg5").feature("lngr1").set("descr", "First principal stress");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").create("gmrk1", "GraphMarker");
    model.result("pg5").feature("lngr1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").feature("gmrk1").set("display", "max");
    model.result("pg5").feature("lngr1").feature("gmrk1").set("precision", 2);
    model.result("pg5").feature("lngr1").feature("gmrk1").set("includeunit", true);
    model.result("pg5").feature("lngr1").feature("gmrk1").set("anchorpoint", "middleright");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("smooth", "none");
    model.result("pg5").run();

    model.component("comp1").physics("solid").feature("lemm1").create("sf1", "Safety", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("sf1").set("FailureCriterion", "Rankine");
    model.component("comp1").physics("solid").feature("lemm1").feature("sf1").set("sigmat_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("sf1").set("sigmat", "100[MPa]");
    model.component("comp1").physics("solid").feature("lemm1").feature("sf1").set("sigmac_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("sf1").set("sigmac", "400[MPa]");

    model.sol("sol1").updateSolution();

    model.result("pg1").set("applyselectiontodatasetedges", false);
    model.result("pg1").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").label("Failure Index (solid)");
    model.result("pg6").label("Failure Index (Safety 1)");
    model.result("pg6").set("showlegends", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"solid.lemm1.sf1.f_i"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "Traffic");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("descractive", false);
    model.result("pg6").label("Failure Index (Safety 1)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormax", 1);
    model.result("pg6").run();
    model.result("pg6").set("view", "new");
    model.result("pg6").run();

    model.view("view2").set("showgrid", false);
    model.view("view2").set("scenelight", false);
    model.view("view2").set("transparency", true);

    model.result("pg6").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("Evaluation Group: Reactions");
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").label("Bolt 1");
    model.result().evaluationGroup("eg1").feature("int1").selection().named("sel1");
    model.result().evaluationGroup("eg1").feature("int1").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("int1").set("unit", new String[]{});
    model.result().evaluationGroup("eg1").feature("int1")
         .set("expr", new String[]{"solid.RFx", "solid.RFy", "solid.RFz"});
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "Bolt 1, X", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "Bolt 1, Y", 1);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "Bolt 1, Z", 2);
    model.result().evaluationGroup("eg1").feature().duplicate("int2", "int1");
    model.result().evaluationGroup("eg1").feature("int2").label("Bolt 2");
    model.result().evaluationGroup("eg1").feature("int2").selection().named("sel2");
    model.result().evaluationGroup("eg1").feature("int2").setIndex("descr", "Bolt 2, X", 0);
    model.result().evaluationGroup("eg1").feature("int2").setIndex("descr", "Bolt 2, Y", 1);
    model.result().evaluationGroup("eg1").feature("int2").setIndex("descr", "Bolt 2, Z", 2);
    model.result().evaluationGroup("eg1").feature().duplicate("int3", "int2");
    model.result().evaluationGroup("eg1").feature("int3").label("Bolt 3");
    model.result().evaluationGroup("eg1").feature("int3").selection().named("sel3");
    model.result().evaluationGroup("eg1").feature("int3").setIndex("descr", "Bolt 3, X", 0);
    model.result().evaluationGroup("eg1").feature("int3").setIndex("descr", "Bolt 3, Y", 1);
    model.result().evaluationGroup("eg1").feature("int3").setIndex("descr", "Bolt 3, Z", 2);
    model.result().evaluationGroup("eg1").feature().duplicate("int4", "int3");
    model.result().evaluationGroup("eg1").feature("int4").label("Bolt 4");
    model.result().evaluationGroup("eg1").feature("int4").selection().named("sel4");
    model.result().evaluationGroup("eg1").feature("int4").setIndex("descr", "Bolt 4, X", 0);
    model.result().evaluationGroup("eg1").feature("int4").setIndex("descr", "Bolt 4, Y", 1);
    model.result().evaluationGroup("eg1").feature("int4").setIndex("descr", "Bolt 4, Z", 2);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();

    model.title("\u652f\u67b6 - \u9759\u6001\u5206\u6790");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u53d7\u9759\u8f7d\u8377\u4f5c\u7528\u7684\u652f\u67b6\u5185\u7684\u7ed3\u6784\u5e94\u529b\u3002");

    model.label("bracket_static.mph");

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");

    model.component("comp1").coordSystem("sys1").set("frametype", "material");

    model.component("comp1").physics("solid").create("bex1", "BaseExcitation", -1);
    model.component("comp1").physics("solid").feature("bex1").set("harmonicPerturbation", true);
    model.component("comp1").physics("solid").feature("bex1").set("ab", new String[]{"3.5*g_const", "0", "0"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("geometricNonlinearity", true);
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("geometricNonlinearity", true);
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"solid/lemm1/dmp1"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("frametype", "spatial");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").label("\u632f\u578b (solid)");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg7").feature("surf1").set("threshold", "manual");
    model.result("pg7").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("colortabletrans", "none");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg7").feature("surf1").create("def", "Deform");
    model.result("pg7").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std2EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result().evaluationGroup().create("std2mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std2mpf1").set("data", "dset2");
    model.result().evaluationGroup("std2mpf1").label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 2)");
    model.result().evaluationGroup("std2mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std2mpf1").run();
    model.result("pg7").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").set("plotgroup", "Default");
    model.study("std3").feature("stat").set("geometricNonlinearity", true);
    model.study("std3").feature("stat").set("solnum", "auto");
    model.study("std3").feature("stat").set("notsolnum", "auto");
    model.study("std3").feature("stat").set("outputmap", new String[]{});
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").create("eig", "Eigenfrequency");
    model.study("std3").feature("eig").set("plotgroup", "Default");
    model.study("std3").feature("eig").set("storefact", false);
    model.study("std3").feature("eig").set("geometricNonlinearity", true);
    model.study("std3").feature("eig").set("outputmap", new String[]{});
    model.study("std3").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").set("ngenAUX", "1");
    model.study("std3").feature("eig").set("goalngenAUX", "1");
    model.study("std3").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std3").create("frmod", "Frequencymodal");
    model.study("std3").feature("frmod").set("geometricNonlinearity", true);
    model.study("std3").feature("frmod").set("solnum", "auto");
    model.study("std3").feature("frmod").set("notsolnum", "auto");
    model.study("std3").feature("frmod").set("outputmap", new String[]{});
    model.study("std3").feature("frmod").setSolveFor("/physics/solid", true);
    model.study("std3").feature("eig").set("useadvanceddisable", true);
    model.study("std3").feature("eig").set("disabledphysics", new String[]{"solid/lemm1/dmp1"});
    model.study("std3").feature("frmod").set("plist", "114.5 115.7 289.9 308.3 365.2 583.5");
    model.study("std3").showAutoSequences("all");

    model.sol("sol4").feature("mo1").set("storelinpoint", true);

    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").setIndex("looplevel", 6, 0);
    model.result("pg8").label("\u5e94\u529b (solid)");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg8").feature("vol1").set("threshold", "manual");
    model.result("pg8").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("vol1").set("colortable", "Rainbow");
    model.result("pg8").feature("vol1").set("colortabletrans", "none");
    model.result("pg8").feature("vol1").set("colorscalemode", "linear");
    model.result("pg8").feature("vol1").set("resolution", "custom");
    model.result("pg8").feature("vol1").set("refine", 2);
    model.result("pg8").feature("vol1").set("colortable", "Prism");
    model.result("pg8").feature("vol1").create("def", "Deform");
    model.result("pg8").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg8").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg8").run();

    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u5706\u89d2");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(24, 25, 70, 71);

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("maxop1").selection().named("sel7");

    model.sol("sol4").updateSolution();

    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("data", "dset4");
    model.result("pg9").label("\u5706\u89d2\u4e2d\u7684\u6700\u5927\u5cf0\u503c von Mises \u5e94\u529b");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "maxop1(solid.misesGp_peak)", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "MPa", 0);
    model.result("pg9").feature("glob1").set("linestyle", "none");
    model.result("pg9").feature("glob1").set("linemarker", "cycle");
    model.result("pg9").feature("glob1").set("legend", false);
    model.result("pg9").run();

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").set("plotgroup", "Default");
    model.study("std4").feature("stat").set("geometricNonlinearity", true);
    model.study("std4").feature("stat").set("solnum", "auto");
    model.study("std4").feature("stat").set("notsolnum", "auto");
    model.study("std4").feature("stat").set("outputmap", new String[]{});
    model.study("std4").feature("stat").set("ngenAUX", "1");
    model.study("std4").feature("stat").set("goalngenAUX", "1");
    model.study("std4").feature("stat").set("ngenAUX", "1");
    model.study("std4").feature("stat").set("goalngenAUX", "1");
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").create("eig", "Eigenfrequency");
    model.study("std4").feature("eig").set("plotgroup", "Default");
    model.study("std4").feature("eig").set("storefact", false);
    model.study("std4").feature("eig").set("geometricNonlinearity", true);
    model.study("std4").feature("eig").set("outputmap", new String[]{});
    model.study("std4").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std4").create("frmod", "Frequencymodal");
    model.study("std4").feature("frmod").set("geometricNonlinearity", true);
    model.study("std4").feature("frmod").set("solnum", "auto");
    model.study("std4").feature("frmod").set("notsolnum", "auto");
    model.study("std4").feature("frmod").set("outputmap", new String[]{});
    model.study("std4").feature("frmod").setSolveFor("/physics/solid", true);
    model.study("std4").feature("eig").set("useadvanceddisable", true);
    model.study("std4").feature("eig").set("disabledphysics", new String[]{"solid/lemm1/dmp1"});
    model.study("std4").feature("frmod").set("plist", "range(100,5,600) range(0.95,0.0025,1.05)*115");
    model.study("std4").showAutoSequences("all");

    model.sol("sol7").feature("mo1").set("storelinpoint", true);

    model.study("std4").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset7");
    model.result("pg10").setIndex("looplevel", 142, 0);
    model.result("pg10").label("\u5e94\u529b (solid) 1");
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").create("vol1", "Volume");
    model.result("pg10").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg10").feature("vol1").set("threshold", "manual");
    model.result("pg10").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg10").feature("vol1").set("colortable", "Rainbow");
    model.result("pg10").feature("vol1").set("colortabletrans", "none");
    model.result("pg10").feature("vol1").set("colorscalemode", "linear");
    model.result("pg10").feature("vol1").set("resolution", "custom");
    model.result("pg10").feature("vol1").set("refine", 2);
    model.result("pg10").feature("vol1").set("colortable", "Prism");
    model.result("pg10").feature("vol1").create("def", "Deform");
    model.result("pg10").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg10").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg10").run();

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("eig").setSolveFor("/physics/ftg", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ftg", true);
    model.study("std3").feature("eig").setSolveFor("/physics/ftg", true);
    model.study("std3").feature("frmod").setSolveFor("/physics/ftg", true);
    model.study("std4").feature("stat").setSolveFor("/physics/ftg", true);
    model.study("std4").feature("eig").setSolveFor("/physics/ftg", true);
    model.study("std4").feature("frmod").setSolveFor("/physics/ftg", true);

    model.component("comp1").physics("ftg").create("vibr1", "VibrationFatigueModel", 2);
    model.component("comp1").physics("ftg").feature("vibr1").selection().named("sel7");
    model.component("comp1").physics("ftg").feature("vibr1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics("ftg").feature("vibr1").set("freqHistoryType", "fhLinearSweep");
    model.component("comp1").physics("ftg").feature("vibr1").set("stressScalar", "effective");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "bracket_fatigue_harmonic_vibration_sn_curve.txt");
    model.func("int1").importData();
    model.func("int1").setIndex("fununit", "Pa", 0);
    model.func("int1").setIndex("argunit", 1, 0);
    model.func("int1").setIndex("argunit", 1, 1);

    model.component("comp1").physics("ftg").feature("vibr1").set("lifeCurve", "int1");

    model.study().create("std5");
    model.study("std5").create("ftge", "Fatigue");
    model.study("std5").feature("ftge").set("ftplistmethod", "manual");
    model.study("std5").feature("ftge").set("solnum", "auto");
    model.study("std5").feature("ftge").set("usesol", "off");
    model.study("std5").feature("ftge").set("outputmap", new String[]{});
    model.study("std5").feature("ftge").setSolveFor("/physics/solid", true);
    model.study("std5").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std5").feature("ftge").set("usesol", true);
    model.study("std5").feature("ftge").set("notsolmethod", "sol");
    model.study("std5").feature("ftge").set("notstudy", "std4");
    model.study("std5").createAutoSequences("all");

    model.sol("sol10").runAll();

    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset10");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"ftg.fus"});
    model.result("pg11").feature("surf1").set("colortable", "Rainbow");
    model.result("pg11").feature("surf1").set("colortabletrans", "none");
    model.result("pg11").feature("surf1").set("colorscalemode", "linear");
    model.result("pg11").feature("surf1").set("colortable", "Traffic");
    model.result("pg11").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg)");
    model.result("pg11").feature("surf1").create("mrkr1", "Marker");
    model.result("pg11").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg11").feature("surf1").feature("mrkr1").set("display", "max");
    model.result("pg11").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg11").run();
    model.result("pg11").create("surf2", "Surface");
    model.result("pg11").feature("surf2").set("expr", "1");
    model.result("pg11").feature("surf2").set("coloring", "uniform");
    model.result("pg11").feature("surf2").set("color", "gray");
    model.result("pg11").feature("surf2").create("tran1", "Transparency");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature("surf2").create("sel1", "Selection");
    model.result("pg11").feature("surf2").feature("sel1").selection().all();
    model.result("pg11").feature("surf2").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88);

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg11").run();
    model.result("pg11").set("view", "view1");
    model.result("pg11").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("vol1").stepFirst(0);
    model.result("pg8").run();
    model.result("pg8").feature("vol1").stepNext(0);
    model.result("pg8").run();
    model.result("pg8").feature("vol1").stepNext(0);
    model.result("pg8").run();
    model.result("pg8").feature("vol1").stepNext(0);
    model.result("pg8").run();
    model.result("pg8").feature("vol1").stepNext(0);
    model.result("pg8").run();
    model.result("pg8").feature("vol1").stepNext(0);
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "");
    model.result("pg9").set("titletype", "none");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u6700\u5927\u5cf0\u503c von Mises \u5e94\u529b (MPa)");
    model.result("pg9").run();
    model.result("pg11").run();

    model.title("\u652f\u67b6 - \u8c10\u6ce2\u632f\u52a8\u75b2\u52b3");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u53d7\u5230\u8c10\u6ce2\u632f\u52a8\u7684\u7ed3\u6784\u8fdb\u884c\u75b2\u52b3\u5206\u6790\uff0c\u5176\u4e2d\u5c06\u8f7d\u8377\u65bd\u52a0\u4e8e\u8be5\u7ed3\u6784\u7684\u8fde\u63a5\u4ef6\u5904\uff0c\u5e76\u6267\u884c\u9891\u7387\u626b\u63cf\u4ee5\u8ba1\u7b97\u75b2\u52b3\u5229\u7528\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();

    model.label("bracket_fatigue_harmonic_vibration.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
