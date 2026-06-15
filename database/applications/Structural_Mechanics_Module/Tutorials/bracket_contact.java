/*
 * bracket_contact.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:18 by COMSOL 6.3.0.290. */
public class bracket_contact {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Tutorials");

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
    model.param().set("dHole", "50[mm]");
    model.param().set("tArm", "8[mm]");
    model.param().set("p0", "Fh/(dHole*tArm*pi/4)");
    model.param().descr("p0", "\u5cf0\u503c\u8f7d\u8377\u5f3a\u5ea6");
    model.param().set("elSize", "6[mm]");
    model.param().descr("elSize", "\u5355\u5143\u5927\u5c0f");
    model.param()
         .comments("The expression for the peak load intensity is obtained by integrating the projection of the pressure distribution in the hole into a total force.\n\n<eqv>F</eqv><sub>h</sub> = <symbol>\u00f2</symbol><eqv>t</eqv> <eqv>p</eqv><sub>0</sub> cos<sup>2</sup> \\philetter <eqv>R</eqv> d\\philetter = \\pi /2 <eqv>p</eqv><sub>0</sub> <eqv>t R </eqv>\n\nHere, <eqv>R</eqv> is the hole radius, and <eqv>t</eqv> is the thickness of the arm. The integral is taken from -\\pi /2 to \\pi /2.");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5206\u5272\u5757");
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

    model.component("comp1").selection("sel1").label("\u87ba\u6813 1");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(41);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u87ba\u6813 2");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(43);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u87ba\u6813 3");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(55);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u87ba\u6813 4");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(57);
    model.component("comp1").selection("sel4").set("groupcontang", true);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u87ba\u6813\u5b54");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2", "sel3", "sel4"});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u5de6\u9500\u5b54");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(4);
    model.component("comp1").selection("sel5").set("groupcontang", true);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u53f3\u9500\u5b54");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(75);
    model.component("comp1").selection("sel6").set("groupcontang", true);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u9500\u5b54");
    model.component("comp1").selection("uni2").set("entitydim", 2);
    model.component("comp1").selection("uni2").set("input", new String[]{"sel5", "sel6"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u87ba\u6813\u5b54\u7684\u8fb9");
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
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
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
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
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
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
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
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
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
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
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
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
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
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();

    model.title("\u652f\u67b6\u51e0\u4f55");

    model
         .description("\u672c\u4f8b\u662f\u6240\u6709\u652f\u67b6\u6559\u7a0b\u7684\u57fa\u7840\uff0c\u5176\u4e2d\u5305\u542b\u652f\u67b6\u51e0\u4f55\u7ed3\u6784\u3002");

    model.label("bracket_basic.mph");

    model.component("comp1").geom("geom1").create("imp2", "Import");
    model.component("comp1").geom("geom1").feature("imp2").set("filename", "bracket_bolt_and_support.mphbin");
    model.component("comp1").geom("geom1").feature("imp2").importData();
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u87ba\u6813");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("imp2(2)");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("pairtype", "contact");
    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("fin").set("repairtol", "1E-3");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Titanium beta-21S");
    model.component("comp1").material("mat2").set("family", "titanium");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "710[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "4940[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "105[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").selection().named("geom1_sel1_dom");

    model.component("comp1").physics("solid").create("pblt1", "BoltPrestress", -1);
    model.component("comp1").physics("solid").feature("pblt1").set("preTensionType", "PreTensionStress");
    model.component("comp1").physics("solid").feature("pblt1").set("s_pre", "400[MPa]");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt1").selection().set(131);
    model.component("comp1").physics("solid").feature("pblt1").create("sblt2", "BoltSelection", 2);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt2").selection().set(136);
    model.component("comp1").physics("solid").feature("pblt1").create("sblt3", "BoltSelection", 2);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt3").selection().set(173);
    model.component("comp1").physics("solid").feature("pblt1").create("sblt4", "BoltSelection", 2);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt4").selection().set(178);
    model.component("comp1").physics("solid").feature("fix1").selection().set(5);
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlPenalty", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("fp_penalty", 10);
    model.component("comp1").physics("solid").feature("dcnt1").set("zeroInitGap", true);
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 2);

    model.component("comp1").pair("ap1").mapping("initial");
    model.component("comp1").pair("ap2").mapping("initial");
    model.component("comp1").pair("ap3").mapping("initial");

    model.component("comp1").coordSystem().create("sys2", "Boundary");
    model.component("comp1").coordSystem("sys2").set("mastercoordsystcomp", "1");

    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 0.1);
    model.component("comp1").physics("solid").feature().duplicate("dcnt2", "dcnt1");
    model.component("comp1").physics("solid").feature("dcnt2").set("pairSelection", "list");
    model.component("comp1").physics("solid").feature("dcnt2").set("pairs", new String[]{"ap2", "ap3"});
    model.component("comp1").physics("solid").feature("dcnt2").feature("fric1").set("mu_fric", 0.2);

    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u87ba\u6813\u5b54\u56f4\u677f");
    model.component("comp1").selection("sel7").geom(1);
    model.component("comp1").selection("sel7").set(11, 13, 16, 18, 35, 37, 40, 42);
    model.component("comp1").selection("sel7").set("groupcontang", true);

    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().named("sel7");
    model.component("comp1").mesh("mesh1").feature("edg2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").selection().named("sel7");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature().move("edg2", 2);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(3);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "elSize*2");
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 3);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 2, 5, 6, 7, 10);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(3, 23, 55, 59, 72, 94);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size2").set("hmax", "elSize*2");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("bolt", "BoltPretension");
    model.study("std1").feature("bolt").set("solnum", "auto");
    model.study("std1").feature("bolt").set("notsolnum", "auto");
    model.study("std1").feature("bolt").set("outputmap", new String[]{});
    model.study("std1").feature("bolt").set("ngenAUX", "1");
    model.study("std1").feature("bolt").set("goalngenAUX", "1");
    model.study("std1").feature("bolt").set("ngenAUX", "1");
    model.study("std1").feature("bolt").set("goalngenAUX", "1");
    model.study("std1").feature("bolt").setSolveFor("/physics/solid", true);
    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("bolt").set("usestol", true);
    model.study("std1").feature("bolt").set("stol", "1e-4");

    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-4");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
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
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset1");
    model.result().evaluationGroup("eg1")
         .label("\u87ba\u6813\u529b: \u87ba\u6813\u9884\u7d27\u529b 1 (\u7814\u7a76 1) (solid)");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").label("Bolt_1");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.solid.pblt1.sblt1.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.solid.pblt1.sblt1.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev2").label("Bolt_2");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "comp1.solid.pblt1.sblt2.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "comp1.solid.pblt1.sblt2.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").create("gev3", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev3").label("Bolt_3");
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "comp1.solid.pblt1.sblt3.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "comp1.solid.pblt1.sblt3.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").create("gev4", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev4").label("Bolt_4");
    model.result().evaluationGroup("eg1").feature("gev4").setIndex("expr", "comp1.solid.pblt1.sblt4.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev4").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev4").setIndex("expr", "comp1.solid.pblt1.sblt4.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev4").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb\uff0c\u9884\u7d27\u529b");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("expr", "solid.disp");
    model.result("pg1").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("vol1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg2").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", 1);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194);
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("arrowbase", "head");
    model.result("pg2").feature("arws1")
         .set("expr", new String[]{"solid.dcnt1.Tnx", "solid.dcnt1.Tny", "solid.dcnt1.Tnz"});
    model.result("pg2").feature("arws1").set("placement", "gausspoints");
    model.result("pg2").feature("arws1").set("gporder", 4);
    model.result("pg2").feature("arws1").label("\u63a5\u89e6 1, \u538b\u529b");
    model.result("pg2").feature("arws1").set("inheritplot", "none");
    model.result("pg2").feature("arws1").set("color", "green");
    model.result("pg2").feature("arws1").create("col", "Color");
    model.result("pg2").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws1").feature("col").set("topcolor", "green");
    model.result("pg2").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg2").feature("arws1").create("def", "Deform");
    model.result("pg2").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws1").feature("def").set("scale", 1);
    model.result("pg2").feature().move("surf1", 1);
    model.result("pg2").create("arws2", "ArrowSurface");
    model.result("pg2").feature("arws2").set("arrowbase", "tail");
    model.result("pg2").feature("arws2")
         .set("expr", new String[]{"solid.dcnt1.Ttx", "solid.dcnt1.Tty", "solid.dcnt1.Ttz"});
    model.result("pg2").feature("arws2").set("placement", "gausspoints");
    model.result("pg2").feature("arws2").set("gporder", 4);
    model.result("pg2").feature("arws2").label("\u63a5\u89e6 1, \u6469\u64e6\u529b");
    model.result("pg2").feature("arws2").set("inheritplot", "none");
    model.result("pg2").feature("arws2").set("color", "magenta");
    model.result("pg2").feature("arws2").create("col", "Color");
    model.result("pg2").feature("arws2").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws2").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws2").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws2").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws2").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws2").feature("col").set("topcolor", "magenta");
    model.result("pg2").feature("arws2").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws2").feature("col")
         .set("custombottomcolor", new double[]{0.54902, 0.509804, 0.54902});
    model.result("pg2").feature("arws2").create("def", "Deform");
    model.result("pg2").feature("arws2").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws2").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arws2").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws2").feature("def").set("scale", 1);
    model.result("pg2").feature().move("surf1", 2);
    model.result("pg2").create("arws3", "ArrowSurface");
    model.result("pg2").feature("arws3").set("arrowbase", "head");
    model.result("pg2").feature("arws3")
         .set("expr", new String[]{"solid.dcnt2.Tnx", "solid.dcnt2.Tny", "solid.dcnt2.Tnz"});
    model.result("pg2").feature("arws3").set("placement", "gausspoints");
    model.result("pg2").feature("arws3").set("gporder", 4);
    model.result("pg2").feature("arws3").label("\u63a5\u89e6 2, \u538b\u529b");
    model.result("pg2").feature("arws3").set("inheritplot", "arws1");
    model.result("pg2").feature("arws3").set("color", "green");
    model.result("pg2").feature("arws3").create("col", "Color");
    model.result("pg2").feature("arws3").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws3").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws3").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws3").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws3").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws3").feature("col").set("topcolor", "green");
    model.result("pg2").feature("arws3").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws3").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg2").feature("arws3").create("def", "Deform");
    model.result("pg2").feature("arws3").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws3").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arws3").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws3").feature("def").set("scale", 1);
    model.result("pg2").feature().move("surf1", 3);
    model.result("pg2").set("legendpos", "rightdouble");
    model.result("pg2").create("arws4", "ArrowSurface");
    model.result("pg2").feature("arws4").set("arrowbase", "tail");
    model.result("pg2").feature("arws4")
         .set("expr", new String[]{"solid.dcnt2.Ttx", "solid.dcnt2.Tty", "solid.dcnt2.Ttz"});
    model.result("pg2").feature("arws4").set("placement", "gausspoints");
    model.result("pg2").feature("arws4").set("gporder", 4);
    model.result("pg2").feature("arws4").label("\u63a5\u89e6 2, \u6469\u64e6\u529b");
    model.result("pg2").feature("arws4").set("inheritplot", "arws2");
    model.result("pg2").feature("arws4").set("color", "magenta");
    model.result("pg2").feature("arws4").create("col", "Color");
    model.result("pg2").feature("arws4").feature("col").set("colortable", "Rainbow");
    model.result("pg2").feature("arws4").feature("col").set("colortabletrans", "none");
    model.result("pg2").feature("arws4").feature("col").set("colorscalemode", "linear");
    model.result("pg2").feature("arws4").feature("col").set("colordata", "arrowlength");
    model.result("pg2").feature("arws4").feature("col").set("coloring", "gradient");
    model.result("pg2").feature("arws4").feature("col").set("topcolor", "magenta");
    model.result("pg2").feature("arws4").feature("col").set("bottomcolor", "custom");
    model.result("pg2").feature("arws4").feature("col")
         .set("custombottomcolor", new double[]{0.54902, 0.509804, 0.54902});
    model.result("pg2").feature("arws4").create("def", "Deform");
    model.result("pg2").feature("arws4").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("arws4").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arws4").feature("def").set("scaleactive", true);
    model.result("pg2").feature("arws4").feature("def").set("scale", 1);
    model.result("pg2").feature().move("surf1", 4);
    model.result("pg2").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg2").run();
    model.result("pg2").label("\u63a5\u89e6\u529b\uff0c\u87ba\u6813");
    model.result("pg2").run();
    model.result("pg2").feature("arws1").active(false);
    model.result("pg2").feature("arws2").active(false);
    model.result("pg2").run();
    model.result("pg2").feature("arws3").set("scaleactive", true);
    model.result("pg2").feature("arws3").set("scale", "1e-11");
    model.result("pg2").feature("arws3").set("inheritplot", "none");
    model.result("pg2").run();
    model.result("pg2").feature("arws4").set("scaleactive", true);
    model.result("pg2").feature("arws4").set("scale", "1E-10");
    model.result("pg2").feature("arws4").set("inheritplot", "none");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_sel1_bnd");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u63a5\u89e6\u538b\u529b\uff0c\u652f\u67b6");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").active(true);
    model.result("pg3").feature("arws1").set("scaleactive", true);
    model.result("pg3").feature("arws1").set("scale", "2e-11");
    model.result("pg3").run();
    model.result("pg3").feature("arws3").active(false);
    model.result("pg3").feature("arws4").active(false);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").label("\u63a5\u89e6\u538b\u529b");
    model.result("pg3").feature("surf1").set("expr", "gpeval(4, solid.Tn)");
    model.result("pg3").feature("surf1").set("coloring", "colortable");
    model.result("pg3").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("tran1").active(false);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("sel1").selection().set(37, 59, 72, 87);
    model.result("pg3").run();

    model.component("comp1").physics("solid").feature().duplicate("dcnt3", "dcnt1");
    model.component("comp1").physics("solid").feature().duplicate("dcnt4", "dcnt2");
    model.component("comp1").physics("solid").feature("dcnt3").set("pairSelection", "list");
    model.component("comp1").physics("solid").feature("dcnt3").set("pairs", new String[]{"ap1"});
    model.component("comp1").physics("solid").feature("dcnt3").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature("dcnt3").set("tunedFor", "Speed");
    model.component("comp1").physics("solid").feature("dcnt4").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature("dcnt4").set("tunedFor", "Speed");

    model.param().set("para", "1");
    model.param().descr("tArm", "\u63a7\u5236\u53c2\u6570");

    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 0.5);
    model.component("comp1").func("step1").set("smooth", 1);

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(26, 98);
    model.component("comp1").physics("solid").feature("bndl1").set("coordinateSystem", "sys1");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "load(-p0,Y-PinHoleY,Z)*step1(para)"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "Fh", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "N", 0);
    model.study("std2").feature("stat").setIndex("pname", "Fh", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "N", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.5,1)", 0);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_solid_Tn_ap1").set("scaleval", "2e8");
    model.sol("sol2").feature("v1").feature("comp1_solid_Tn_ap2").set("scaleval", "2e8");
    model.sol("sol2").feature("v1").feature("comp1_solid_Tn_ap3").set("scaleval", "2e8");
    model.sol("sol2").feature("v1").feature("comp1_solid_Tt_ap1").set("scaleval", "2e7");
    model.sol("sol2").feature("v1").feature("comp1_solid_Tt_ap2").set("scaleval", "2e7");
    model.sol("sol2").feature("v1").feature("comp1_solid_Tt_ap3").set("scaleval", "2e7");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").label("\u5e94\u529b (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "Rainbow");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").create("def", "Deform");
    model.result("pg4").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("vol1").feature("def").set("scale", "1");
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").set("data", "dset2");
    model.result().evaluationGroup("eg2")
         .label("\u87ba\u6813\u529b: \u87ba\u6813\u9884\u7d27\u529b 1 (\u7814\u7a76 2) (solid)");
    model.result().evaluationGroup("eg2").set("transpose", true);
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").label("Bolt_1");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "comp1.solid.pblt1.sblt1.F_bolt", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "comp1.solid.pblt1.sblt1.F_shear", 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg2").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev2").label("Bolt_2");
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("expr", "comp1.solid.pblt1.sblt2.F_bolt", 0);
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("expr", "comp1.solid.pblt1.sblt2.F_shear", 1);
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg2").create("gev3", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev3").label("Bolt_3");
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("expr", "comp1.solid.pblt1.sblt3.F_bolt", 0);
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("expr", "comp1.solid.pblt1.sblt3.F_shear", 1);
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg2").create("gev4", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev4").label("Bolt_4");
    model.result().evaluationGroup("eg2").feature("gev4").setIndex("expr", "comp1.solid.pblt1.sblt4.F_bolt", 0);
    model.result().evaluationGroup("eg2").feature("gev4").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg2").feature("gev4").setIndex("expr", "comp1.solid.pblt1.sblt4.F_shear", 1);
    model.result().evaluationGroup("eg2").feature("gev4").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg2").run();
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb\uff0c\u5de5\u4f5c\u8f7d\u8377");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").set("expr", "solid.disp");
    model.result("pg4").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg4").feature("vol1").create("mrkr1", "Marker");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").feature("mrkr1").set("precision", 3);
    model.result("pg4").feature("vol1").feature("mrkr1").set("display", "max");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg5").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def").set("scale", 1);
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194);
    model.result("pg5").feature("surf1").create("tran1", "Transparency");
    model.result("pg5").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("arrowbase", "head");
    model.result("pg5").feature("arws1")
         .set("expr", new String[]{"solid.dcnt3.Tnx", "solid.dcnt3.Tny", "solid.dcnt3.Tnz"});
    model.result("pg5").feature("arws1").set("placement", "gausspoints");
    model.result("pg5").feature("arws1").set("gporder", 4);
    model.result("pg5").feature("arws1").label("\u63a5\u89e6 3, \u538b\u529b");
    model.result("pg5").feature("arws1").set("inheritplot", "none");
    model.result("pg5").feature("arws1").set("color", "green");
    model.result("pg5").feature("arws1").create("col", "Color");
    model.result("pg5").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg5").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg5").feature("arws1").feature("col").set("topcolor", "green");
    model.result("pg5").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg5").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg5").feature("arws1").create("def", "Deform");
    model.result("pg5").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("arws1").feature("def").set("scale", 1);
    model.result("pg5").feature().move("surf1", 1);
    model.result("pg5").create("arws2", "ArrowSurface");
    model.result("pg5").feature("arws2").set("arrowbase", "tail");
    model.result("pg5").feature("arws2")
         .set("expr", new String[]{"solid.dcnt3.Ttx", "solid.dcnt3.Tty", "solid.dcnt3.Ttz"});
    model.result("pg5").feature("arws2").set("placement", "gausspoints");
    model.result("pg5").feature("arws2").set("gporder", 4);
    model.result("pg5").feature("arws2").label("\u63a5\u89e6 3, \u6469\u64e6\u529b");
    model.result("pg5").feature("arws2").set("inheritplot", "none");
    model.result("pg5").feature("arws2").set("color", "magenta");
    model.result("pg5").feature("arws2").create("col", "Color");
    model.result("pg5").feature("arws2").feature("col").set("colortable", "Rainbow");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg5").feature("arws2").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("arws2").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("arws2").feature("col").set("colordata", "arrowlength");
    model.result("pg5").feature("arws2").feature("col").set("coloring", "gradient");
    model.result("pg5").feature("arws2").feature("col").set("topcolor", "magenta");
    model.result("pg5").feature("arws2").feature("col").set("bottomcolor", "custom");
    model.result("pg5").feature("arws2").feature("col")
         .set("custombottomcolor", new double[]{0.54902, 0.509804, 0.54902});
    model.result("pg5").feature("arws2").create("def", "Deform");
    model.result("pg5").feature("arws2").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("arws2").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").feature("arws2").feature("def").set("scaleactive", true);
    model.result("pg5").feature("arws2").feature("def").set("scale", 1);
    model.result("pg5").feature().move("surf1", 2);
    model.result("pg5").create("arws3", "ArrowSurface");
    model.result("pg5").feature("arws3").set("arrowbase", "head");
    model.result("pg5").feature("arws3")
         .set("expr", new String[]{"solid.dcnt4.Tnx", "solid.dcnt4.Tny", "solid.dcnt4.Tnz"});
    model.result("pg5").feature("arws3").set("placement", "gausspoints");
    model.result("pg5").feature("arws3").set("gporder", 4);
    model.result("pg5").feature("arws3").label("\u63a5\u89e6 4, \u538b\u529b");
    model.result("pg5").feature("arws3").set("inheritplot", "arws1");
    model.result("pg5").feature("arws3").set("color", "green");
    model.result("pg5").feature("arws3").create("col", "Color");
    model.result("pg5").feature("arws3").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("arws3").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("arws3").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("arws3").feature("col").set("colordata", "arrowlength");
    model.result("pg5").feature("arws3").feature("col").set("coloring", "gradient");
    model.result("pg5").feature("arws3").feature("col").set("topcolor", "green");
    model.result("pg5").feature("arws3").feature("col").set("bottomcolor", "custom");
    model.result("pg5").feature("arws3").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg5").feature("arws3").create("def", "Deform");
    model.result("pg5").feature("arws3").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("arws3").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").feature("arws3").feature("def").set("scaleactive", true);
    model.result("pg5").feature("arws3").feature("def").set("scale", 1);
    model.result("pg5").feature().move("surf1", 3);
    model.result("pg5").set("legendpos", "rightdouble");
    model.result("pg5").create("arws4", "ArrowSurface");
    model.result("pg5").feature("arws4").set("arrowbase", "tail");
    model.result("pg5").feature("arws4")
         .set("expr", new String[]{"solid.dcnt4.Ttx", "solid.dcnt4.Tty", "solid.dcnt4.Ttz"});
    model.result("pg5").feature("arws4").set("placement", "gausspoints");
    model.result("pg5").feature("arws4").set("gporder", 4);
    model.result("pg5").feature("arws4").label("\u63a5\u89e6 4, \u6469\u64e6\u529b");
    model.result("pg5").feature("arws4").set("inheritplot", "arws2");
    model.result("pg5").feature("arws4").set("color", "magenta");
    model.result("pg5").feature("arws4").create("col", "Color");
    model.result("pg5").feature("arws4").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("arws4").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("arws4").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("arws4").feature("col").set("colordata", "arrowlength");
    model.result("pg5").feature("arws4").feature("col").set("coloring", "gradient");
    model.result("pg5").feature("arws4").feature("col").set("topcolor", "magenta");
    model.result("pg5").feature("arws4").feature("col").set("bottomcolor", "custom");
    model.result("pg5").feature("arws4").feature("col")
         .set("custombottomcolor", new double[]{0.54902, 0.509804, 0.54902});
    model.result("pg5").feature("arws4").create("def", "Deform");
    model.result("pg5").feature("arws4").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("arws4").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").feature("arws4").feature("def").set("scaleactive", true);
    model.result("pg5").feature("arws4").feature("def").set("scale", 1);
    model.result("pg5").feature().move("surf1", 4);
    model.result("pg5").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg5").run();
    model.result("pg5").label("\u63a5\u89e6\u529b\uff0c\u5de5\u4f5c\u8f7d\u8377");
    model.result("pg5").run();
    model.result("pg5").feature("arws1").set("scaleactive", true);
    model.result("pg5").feature("arws1").set("scale", "1e-11");
    model.result("pg5").run();
    model.result("pg5").feature("arws2").set("scaleactive", true);
    model.result("pg5").feature("arws2").set("scale", "1e-10");
    model.result("pg5").feature("surf1").feature("sel1").selection().named("geom1_sel1_bnd");
    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(37, 59, 72, 87, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194);
    model.result("pg5").run();

    model.study("std1").feature("bolt").set("useadvanceddisable", true);
    model.study("std1").feature("bolt")
         .set("disabledphysics", new String[]{"solid/dcnt3", "solid/dcnt4", "solid/bndl1"});

    model.result("pg4").run();

    model.title("\u652f\u67b6 - \u63a5\u89e6\u5206\u6790");

    model
         .description("\u672c\u4f8b\u4ecb\u7ecd\u7ed3\u6784\u529b\u5b66\u95ee\u9898\u4e2d\u7684\u63a5\u89e6\u5bf9\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u5b83\u4eec\u6765\u6a21\u62df\u88c5\u914d\u4e2d\u4e0d\u540c\u96f6\u4ef6\u4e4b\u95f4\u7684\u63a5\u89e6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("bracket_contact.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
