/*
 * bracket_fatigue_random_vibration.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:02 by COMSOL 6.3.0.290. */
public class bracket_fatigue_random_vibration {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Random_Vibration");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "bracket.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.param().set("Fh", "800[N]");
    model.param().descr("Fh", "Force per hole");
    model.param().set("dHole", "50[mm]");
    model.param().descr("dHole", "Hole diameter");
    model.param().set("tArm", "8[mm]");
    model.param().descr("tArm", "Thickness of arm");
    model.param().set("p0", "Fh/(dHole*tArm*pi/4)");
    model.param().descr("p0", "Peak load intensity");
    model.param().set("elSize", "6[mm]");
    model.param().descr("elSize", "Element size");
    model.param()
         .comments("The expression for the peak load intensity is obtained by integrating the projection of the pressure distribution in the hole into a total force.\n\n<eqv>F</eqv><sub>h</sub> = <symbol>\u00f2</symbol><eqv>t</eqv> <eqv>p</eqv><sub>0</sub> cos<sup>2</sup> \\philetter <eqv>R</eqv> d\\philetter = \\pi /2 <eqv>p</eqv><sub>0</sub> <eqv>t R </eqv>\n\nHere, <eqv>R</eqv> is the hole radius, and <eqv>t</eqv> is the thickness of the arm. The integral is taken from -\\pi /2 to \\pi /2.");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Partition block");
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
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("cm1", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm1").selection("ent").set("par1", 2, 5);
    model.component("comp1").geom("geom1").run("cm1");
    model.component("comp1").geom("geom1").feature("cm1").setIndex("parname", "PinHoleX", 0);
    model.component("comp1").geom("geom1").feature("cm1").setIndex("parname", "PinHoleY", 1);
    model.component("comp1").geom("geom1").feature("cm1").setIndex("parname", "PinHoleZ", 2);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("Bolt 1");
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
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2", "sel3", "sel4"});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("Left Pin Hole");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(4);
    model.component("comp1").selection("sel5").set("groupcontang", true);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("Right Pin Hole");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(75);
    model.component("comp1").selection("sel6").set("groupcontang", true);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("Pin Holes");
    model.component("comp1").selection("uni2").set("entitydim", 2);
    model.component("comp1").selection("uni2").set("input", new String[]{"sel5", "sel6"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("Bolt Hole Edges");
    model.component("comp1").selection("adj1").set("entitydim", 2);
    model.component("comp1").selection("adj1").set("outputdim", 1);
    model.component("comp1").selection("adj1").set("input", new String[]{"uni1"});

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "load");
    model.component("comp1").func("an1").set("expr", "F*cos(atan2(py,abs(px)))");
    model.component("comp1").func("an1").set("args", "F, py, px");
    model.component("comp1").func("an1").setIndex("argunit", "Pa", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").setIndex("argunit", "m", 2);
    model.component("comp1").func("an1").set("fununit", "Pa");

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
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").run();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "Displacement", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "Stress tensor", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();

    model.title("\u652f\u67b6\u51e0\u4f55");

    model
         .description("\u672c\u4f8b\u662f\u6240\u6709\u652f\u67b6\u6559\u7a0b\u7684\u57fa\u7840\uff0c\u5176\u4e2d\u5305\u542b\u652f\u67b6\u51e0\u4f55\u7ed3\u6784\u3002");

    model.label("bracket_basic.mph");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);
    model.study().create("std2");
    model.study("std2").create("mr", "ModelReduction");
    model.study("std2").feature("mr").setSolveFor("/physics/solid", true);

    model.common().create("grmi1", "GlobalReducedModelInputs", "");

    model.reduced().create("rom1", "ModalFrequency");
    model.reduced().create("rvib1", "RandomVibration");
    model.reduced("rvib1").set("frequencyResponseModel", "rom1");

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 12);
    model.study("std1").feature("eig").set("shiftactive", true);
    model.study("std1").feature("eig").set("shift", "1");
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").feature("mr").set("trainingStudy", "std1");
    model.study("std2").feature("mr").set("trainingStep", "eig");
    model.study("std2").feature("mr").feature().create("freq1", "Frequency");
    model.study("std2").feature("mr").feature("freq1").set("plist", "100");
    model.study("std2").feature("mr").set("unreducedModelStudy", "std2");
    model.study("std2").feature("mr").set("unreducedModelStep", "freq1");
    model.study("std2").feature("mr").set("romdata", "rom1");

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("f1", 400);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("zeta1", 0.05);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("f2", 700);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("zeta2", 0.03);

    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"solid/lemm1/dmp1"});

    model.common("grmi1").setIndex("name", "Fx", 0);
    model.common("grmi1").setIndex("expression", "100[N]", 0);
    model.common("grmi1").setIndex("name", "Fz", 1);
    model.common("grmi1").setIndex("expression", "100[N]", 1);

    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig1").selection().named("uni2");
    model.component("comp1").physics("solid").prop("PhysicsSymbols").set("PhysicsSymbols", true);
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 1);
    model.component("comp1").physics("solid").feature("rig1").set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("solid").feature("rig1").setIndex("ConstrainedRotation", true, 0);
    model.component("comp1").physics("solid").feature("rig1").setIndex("ConstrainedRotation", true, 1);
    model.component("comp1").physics("solid").feature("rig1").setIndex("ConstrainedRotation", true, 2);
    model.component("comp1").physics("solid").feature("rig1").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rf1")
         .set("Ft", new String[]{"Fx", "0", "Fz"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("U", "at3(0, 0, -0.045, u)");
    model.component("comp1").variable("var1").descr("U", "Displacement, X component");
    model.component("comp1").variable("var1").set("W", "at3(0, 0, -0.045, w)");
    model.component("comp1").variable("var1").descr("W", "Displacement, Z component");

    model.study("std2").feature("mr").feature("freq1").set("plist", 500);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("Mode Shape (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "Displacement field");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq").label("Eigenfrequencies (Study 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "Angular frequency", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "Damping ratio", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "Quality factor", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").label("PSD of load");
    model.func("int1").set("funcname", "PSD");
    model.func("int1").setIndex("table", 400, 0, 0);
    model.func("int1").setIndex("table", 0.01, 0, 1);
    model.func("int1").setIndex("table", 500, 1, 0);
    model.func("int1").setIndex("table", "5^2", 1, 1);
    model.func("int1").setIndex("table", 600, 2, 0);
    model.func("int1").setIndex("table", "10^2", 2, 1);
    model.func("int1").setIndex("table", 700, 3, 0);
    model.func("int1").setIndex("table", "3^2", 3, 1);
    model.func("int1").setIndex("table", 800, 4, 0);
    model.func("int1").setIndex("table", 0.01, 4, 1);
    model.func("int1").setIndex("argunit", "Hz", 0);
    model.func("int1").setIndex("fununit", "N^2/Hz", 0);
    model.func("int1").set("argtrans", "logarithmic");

    return model;
  }

  public static Model run2(Model model) {
    model.func("int1").set("valtrans", "logarithmic");

    model.reduced("rvib1").setIndex("powerSpectralDensity", "PSD(freq+250[Hz])", 0);
    model.reduced("rvib1").setIndex("powerSpectralDensity", "PSD(freq)", 1);
    model.reduced("rvib1").set("fLow", 150);
    model.reduced("rvib1").set("fHigh", 800);

    model.sol("sol2").updateSolution();

    model.result().numerical().create("gevs1", "EvalGlobalSweep");
    model.result().numerical("gevs1").set("data", "dset2");
    model.result().numerical("gevs1").setIndex("pname", "freq", 0);
    model.result().numerical("gevs1").setIndex("plistarr", "range(150,5,800)[Hz]", 0);
    model.result().numerical("gevs1").setIndex("expr", "PSD(freq+250[Hz])", 0);
    model.result().numerical("gevs1").setIndex("unit", "N^2/Hz", 0);
    model.result().numerical("gevs1").setIndex("descr", "PSD of load, X component", 0);
    model.result().numerical("gevs1").setIndex("expr", "PSD(freq)", 1);
    model.result().numerical("gevs1").setIndex("unit", "N^2/Hz", 1);
    model.result().numerical("gevs1").setIndex("descr", "PSD of load, Z component", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Global Evaluation Sweep 1");
    model.result().numerical("gevs1").set("table", "tbl1");
    model.result().numerical("gevs1").setResult();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("source", "table");
    model.result("pg2").feature("tblp1").set("table", "tbl1");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("linemarker", "cycle");
    model.result("pg2").feature("tblp1").set("markerpos", "interp");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").run();
    model.result("pg2").label("PSD of Loads");
    model.result("pg2").set("legendpos", "middleleft");
    model.result().numerical().create("gevs2", "EvalGlobalSweep");
    model.result().numerical("gevs2").setIndex("pname", "freq", 0);
    model.result().numerical("gevs2").setIndex("plistarr", "range(150,5,800)[Hz]", 0);
    model.result().numerical("gevs2").setIndex("expr", "rvib1.psd(U)", 0);
    model.result().numerical("gevs2").setIndex("unit", "m^2/Hz", 0);
    model.result().numerical("gevs2").setIndex("descr", "PSD of U displacement", 0);
    model.result().numerical("gevs2").setIndex("expr", "rvib1.psd(W)", 1);
    model.result().numerical("gevs2").setIndex("unit", "m^2/Hz", 1);
    model.result().numerical("gevs2").setIndex("descr", "PSD of W displacement", 1);
    model.result().numerical("gevs2").set("data", "dset2");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Global Evaluation Sweep 2");
    model.result().numerical("gevs2").set("table", "tbl2");
    model.result().numerical("gevs2").setResult();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("source", "table");
    model.result("pg3").feature("tblp1").set("table", "tbl2");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").set("linemarker", "cycle");
    model.result("pg3").feature("tblp1").set("markerpos", "interp");
    model.result("pg3").set("xlog", true);
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").run();
    model.result("pg3").label("PSD of Displacements");
    model.result("pg3").set("legendpos", "uppermiddle");
    model.result().numerical().create("gevs3", "EvalGlobalSweep");
    model.result().numerical("gevs3").setIndex("pname", "freq", 0);
    model.result().numerical("gevs3").setIndex("plistarr", "range(150,5,800)[Hz]", 0);
    model.result().numerical("gevs3").setIndex("expr", "real(rvib1.cross(U,W))", 0);
    model.result().numerical("gevs3").setIndex("unit", "m^2/Hz", 0);
    model.result().numerical("gevs3").setIndex("descr", "Cross-correlation of U and W, real part", 0);
    model.result().numerical("gevs3").setIndex("expr", "imag(rvib1.cross(U,W))", 1);
    model.result().numerical("gevs3").setIndex("unit", "m^2/Hz", 1);
    model.result().numerical("gevs3").setIndex("descr", "Cross-correlation of U and W, imaginary part", 1);
    model.result().numerical("gevs3").setIndex("expr", "abs(rvib1.cross(U,W))", 2);
    model.result().numerical("gevs3").setIndex("unit", "m^2/Hz", 2);
    model.result().numerical("gevs3").setIndex("descr", "Cross-correlation of U and W, absolute value", 2);
    model.result().numerical("gevs3").set("data", "dset2");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("Global Evaluation Sweep 3");
    model.result().numerical("gevs3").set("table", "tbl3");
    model.result().numerical("gevs3").setResult();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("source", "table");
    model.result("pg4").feature("tblp1").set("table", "tbl3");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").run();
    model.result("pg4").set("xlog", true);
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").run();
    model.result("pg4").label("Cross Correlation (U,V)");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "Frequency [Hz]");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Cross correlation (U,V) [m^2/Hz]");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("PSD, u-displacement");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "rvib1.psd(u)");
    model.result("pg5").feature("surf1").set("descractive", true);
    model.result("pg5").feature("surf1").set("descr", "Displacement, X component, PSD");
    model.result("pg5").feature("surf1").set("unit", "m^2/Hz");
    model.result("pg5").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("PSD, w-displacement");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "rvib1.psd(w)");
    model.result("pg6").feature("surf1").set("descr", "Displacement, Z component, PSD");
    model.result("pg6").run();
    model.result().setOnlyPlotWhenRequested(true);
    model.result().duplicate("pg7", "pg5");
    model.result("pg7").label("RMS, u-displacement");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("solutionintitle", false);
    model.result("pg7").feature("surf1").set("expr", "rvib1.rms(u)");
    model.result("pg7").feature("surf1").set("descr", "Displacement, X component, RMS");
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").label("RMS, w-displacement");
    model.result("pg8").feature("surf1").set("expr", "rvib1.rms(w)");
    model.result("pg8").feature("surf1").set("descr", "Displacement, Z component, RMS");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("RMS, von Mises stress");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "rvib1.q2sq(solid.mises_rv)");
    model.result("pg9").feature("surf1").set("descractive", true);
    model.result("pg9").feature("surf1").set("descr", "Stress, von Mises, RMS");
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").label("RMS, displacement");
    model.result("pg10").feature("surf1").set("expr", "rvib1.q2sq(solid.disp_rv)");
    model.result("pg10").feature("surf1").set("descr", "Displacement, magnitude, RMS");
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").label("RMS, acceleration");
    model.result("pg11").feature("surf1").set("expr", "rvib1.q2sq(solid.utt_rv)");
    model.result("pg11").feature("surf1").set("descr", "Acceleration, magnitude, RMS");
    model.result("pg11").run();

    model.title("\u652f\u67b6 - \u968f\u673a\u632f\u52a8\u5206\u6790");

    model
         .description("\u672c\u6559\u5b66\u6848\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u529f\u7387\u8c31\u5bc6\u5ea6 (PSD) \u5bf9\u7ed3\u6784\u8fdb\u884c\u968f\u673a\u632f\u52a8\u5206\u6790\uff0c\u5e76\u57fa\u4e8e\u6a21\u5f0f\u964d\u9636\u6a21\u578b (ROM) \u8fdb\u884c\u8ba1\u7b97\u3002");

    model.label("bracket_random_vibration.mph");

    model.param().rename("dHole", "b");
    model.param().set("b", "-0.25");
    model.param().descr("tArm", "Basquin \u6307\u6570");
    model.param().rename("tArm", "sigf");
    model.param().set("sigf", "2.2[GPa]");
    model.param().descr("sigf", "Basquin \u5f3a\u5ea6\u7cfb\u6570");

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("eig").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("mr").feature("freq1").setSolveFor("/physics/ftg", true);

    model.component("comp1").physics("ftg").create("rand1", "RandomFatigueModel", 2);
    model.component("comp1").physics("ftg").feature("rand1").selection().all();
    model.component("comp1").physics("ftg").feature("rand1").set("rv", "rvib1");
    model.component("comp1").physics("ftg").feature("rand1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics("ftg").feature("rand1").set("fl", 150);
    model.component("comp1").physics("ftg").feature("rand1").set("fu", 800);
    model.component("comp1").physics("ftg").feature("rand1").set("ftgCriterion", "Basquin");
    model.component("comp1").physics("ftg").feature("rand1").set("sigmaf_Basquin_mat", "userdef");
    model.component("comp1").physics("ftg").feature("rand1").set("sigmaf_Basquin", "sigf");
    model.component("comp1").physics("ftg").feature("rand1").set("b_Basquin_mat", "userdef");
    model.component("comp1").physics("ftg").feature("rand1").set("b_Basquin", "b");
    model.component("comp1").physics("ftg").feature("rand1").set("Lcut", "1e3[h]");
    model.component("comp1").physics("ftg").feature().duplicate("rand2", "rand1");
    model.component("comp1").physics("ftg").feature("rand2").set("cyclecountingmodel", "bendat");

    model.study().create("std3");
    model.study("std3").create("ftge", "Fatigue");
    model.study("std3").feature("ftge").set("ftplistmethod", "manual");
    model.study("std3").feature("ftge").set("solnum", "auto");
    model.study("std3").feature("ftge").set("usesol", "off");
    model.study("std3").feature("ftge").set("outputmap", new String[]{});
    model.study("std3").feature("ftge").setSolveFor("/physics/solid", true);
    model.study("std3").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "dset4");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", new String[]{"ftg.life"});
    model.result("pg12").feature("surf1").set("colortable", "Rainbow");
    model.result("pg12").feature("surf1").set("colortabletrans", "none");
    model.result("pg12").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg12").feature("surf1").set("colortablerev", true);
    model.result("pg12").feature("surf1").set("colortable", "Traffic");
    model.result("pg12").label("\u75b2\u52b3\u5bff\u547d (ftg)");
    model.result("pg12").feature("surf1").create("mrkr1", "Marker");
    model.result("pg12").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg12").feature("surf1").feature("mrkr1").set("display", "min");
    model.result("pg12").label("\u75b2\u52b3\u5bff\u547d (Dirlik)");
    model.result("pg12").feature("surf1").set("expr", "ftg.rand1.life");
    model.result("pg12").feature("surf1").set("descr", "\u75b2\u52b3\u5bff\u547d");
    model.result("pg12").feature("surf1").set("unit", "h");
    model.result("pg12").feature("surf1").feature("mrkr1").set("backgroundcolor", "fromtheme");
    model.result("pg12").feature("surf1").feature("mrkr1").set("anchorpoint", "lowerleft");
    model.result("pg12").run();
    model.result().duplicate("pg13", "pg12");
    model.result("pg13").label("\u75b2\u52b3\u5bff\u547d (Bendat)");
    model.result("pg13").feature("surf1").set("expr", "ftg.rand2.life");
    model.result("pg13").run();

    model.title("\u652f\u67b6 - \u968f\u673a\u632f\u52a8\u75b2\u52b3");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u53d7\u5230\u968f\u673a\u632f\u52a8\u7684\u7ed3\u6784\u8fdb\u884c\u75b2\u52b3\u5206\u6790\u3002");

    model.label("bracket_fatigue_random_vibration.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
