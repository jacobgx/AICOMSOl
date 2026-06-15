/*
 * beam_solid_connection.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:59 by COMSOL 6.3.0.290. */
public class beam_solid_connection {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Beams_and_Shells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("beam", "HermitianBeam", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/beam", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "1", "[m] \u6881\u7684\u957f\u5ea6");
    model.param().set("W", "0.2", "[m] \u5bbd\u5ea6");
    model.param().set("H", "0.1", "[m] \u9ad8\u5ea6");
    model.param().set("R", "0.015", "[m] \u5706\u7684\u534a\u5f84");
    model.param().set("A", "7.64E-04", "[m^2] \u6881\u7684\u6a2a\u622a\u9762\u79ef");
    model.param().set("Izz", "8.01377E-07", "[m^4] \u5c40\u90e8 z \u8f74\u7684\u60ef\u6027\u77e9");
    model.param()
         .set("ez", "0", "[m] \u5230\u526a\u5207\u4e2d\u5fc3\u7684\u8ddd\u79bb\uff0c\u5c40\u90e8 z \u65b9\u5411");
    model.param().set("Iyy", "8.48903E-08", "[m^4] \u5c40\u90e8 y \u8f74\u7684\u60ef\u6027\u77e9");
    model.param()
         .set("ey", "0", "[m] \u5230\u526a\u5207\u4e2d\u5fc3\u7684\u8ddd\u79bb\uff0c\u5c40\u90e8 y \u65b9\u5411");
    model.param().set("J", "6.72659E-09", "[m^4] \u626d\u8f6c\u5e38\u6570");
    model.param().set("hy", "0.08", "[m] \u5c40\u90e8 y \u65b9\u5411\u7684\u622a\u9762\u9ad8\u5ea6");
    model.param().set("hz", "0.046", "[m] \u5c40\u90e8 z \u65b9\u5411\u7684\u622a\u9762\u9ad8\u5ea6");
    model.param().set("Wt", "8.34675E-07", "[m^3] \u6297\u626d\u622a\u9762\u6a21\u91cf");
    model.param()
         .set("muy", "2.913993626", "[1] \u5c40\u90e8 y \u65b9\u5411\u7684\u6700\u5927\u526a\u5207\u5e94\u529b\u56e0\u5b50");
    model.param()
         .set("muz", "2.438020494", "[1] \u5c40\u90e8 z \u65b9\u5411\u7684\u6700\u5927\u526a\u5207\u5e94\u529b\u56e0\u5b50");

    model.group().create("lg1", "LoadGroup");
    model.group("lg1").label("\u8f7d\u8377\u7ec4 Mx");
    model.group("lg1").paramName("lgMx");
    model.group().create("lg2", "LoadGroup");
    model.group("lg2").label("\u8f7d\u8377\u7ec4 My");
    model.group("lg2").paramName("lgMy");
    model.group().create("lg3", "LoadGroup");
    model.group("lg3").label("\u8f7d\u8377\u7ec4 Mz");
    model.group("lg3").paramName("lgMz");
    model.group().create("lg4", "LoadGroup");
    model.group("lg4").label("\u8f7d\u8377\u7ec4 Fx");
    model.group("lg4").paramName("lgFx");
    model.group().create("lg5", "LoadGroup");
    model.group("lg5").label("\u8f7d\u8377\u7ec4 Fy");
    model.group("lg5").paramName("lgFy");
    model.group().create("lg6", "LoadGroup");
    model.group("lg6").label("\u8f7d\u8377\u7ec4 Fz");
    model.group("lg6").paramName("lgFz");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "L 2*L");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "0 0");
    model.component("comp1").geom("geom1").feature("pol1").set("z", "0 0");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zy");
    model.component("comp1").geom("geom1").run("wp1");
    model.geom()
         .load(new String[]{"part1"}, "Structural_Mechanics_Module\\Beams\\European_Standard\\IPE_beam.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pi1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"L/2", "0", "-L/2"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("dif1", "pol1");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", "0.3*L");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "zy");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", "0.45*L");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().duplicate("wp3", "wp2");
    model.component("comp1").geom("geom1").feature("wp3").set("quickx", "0.55*L");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("copy1(1)", "dif1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").feature().duplicate("par2", "par1");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("par1");
    model.component("comp1").geom("geom1").feature("par2").set("workplane", "wp3");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("par2(2)");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("par2(1)");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u56fa\u5b9a\u7aef");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 1, 69);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u5b9e\u4f53-\u6881\u754c\u9762");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 68, 136);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u81ea\u7531\u7aef");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(0);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 178, 180);

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("genext1").selection().set(4, 5, 6);
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"X", "Y+0.3*L", "Z"});
    model.component("comp1").cpl("genext1").set("srcframe", "material");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.material("mat1").propertyGroup().create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.material("mat1").label("Structural steel");
    model.material("mat1").set("family", "custom");
    model.material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat1").set("diffuse", "custom");
    model.material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("ambient", "custom");
    model.material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("noise", true);
    model.material("mat1").set("fresnel", 0.9);
    model.material("mat1").set("roughness", 0.3);
    model.material("mat1").set("diffusewrap", 0);
    model.material("mat1").set("reflectance", 0);
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.material("mat1").propertyGroup("Enu").addInput("temperature");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.material("mat1").propertyGroup("ElastoplasticModel").label("Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.material("mat1").propertyGroup("Voce").addInput("temperature");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.material("mat1").propertyGroup("Norton").label("Norton");
    model.material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").label("Chaboche viscoplasticity");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().geom("geom1", 1);
    model.component("comp1").material("matlnk2").selection().set(309, 310);

    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_sel1");
    model.component("comp1").physics("beam").selection().set(309, 310);
    model.component("comp1").physics("beam").feature("csd1").set("area", "A");
    model.component("comp1").physics("beam").feature("csd1").set("Izz", "Izz");
    model.component("comp1").physics("beam").feature("csd1").set("ez", "ez");
    model.component("comp1").physics("beam").feature("csd1").set("Iyy", "Iyy");
    model.component("comp1").physics("beam").feature("csd1").set("ey", "ey");
    model.component("comp1").physics("beam").feature("csd1").set("J_beam", "J");
    model.component("comp1").physics("beam").feature("csd1").set("hy", "hy");
    model.component("comp1").physics("beam").feature("csd1").set("hz", "hz");
    model.component("comp1").physics("beam").feature("csd1").set("Wt", "Wt");
    model.component("comp1").physics("beam").feature("csd1").set("muy", "muy");
    model.component("comp1").physics("beam").feature("csd1").set("muz", "muz");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("vector_beam", new int[]{0, 1, 0});
    model.component("comp1").physics("beam").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl1").label("\u70b9\u8f7d\u8377\uff0cMx");
    model.component("comp1").physics("beam").feature("pl1").selection().named("geom1_sel3");
    model.component("comp1").physics("beam").feature("pl1").set("momentPoint", new int[]{1, 0, 0});
    model.component("comp1").physics("beam").feature("pl1").set("loadGroup", "lg1");
    model.component("comp1").physics("beam").create("pl2", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl2").label("\u70b9\u8f7d\u8377\uff0cMy");
    model.component("comp1").physics("beam").feature("pl2").selection().named("geom1_sel3");
    model.component("comp1").physics("beam").feature("pl2").set("momentPoint", new int[]{0, 1, 0});
    model.component("comp1").physics("beam").feature("pl2").set("loadGroup", "lg2");
    model.component("comp1").physics("beam").create("pl3", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl3").selection().named("geom1_sel3");
    model.component("comp1").physics("beam").feature("pl3").label("\u70b9\u8f7d\u8377\uff0cMz");
    model.component("comp1").physics("beam").feature("pl3").set("momentPoint", new int[]{0, 0, 1});
    model.component("comp1").physics("beam").feature("pl3").set("loadGroup", "lg3");
    model.component("comp1").physics("beam").create("pl4", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl4").label("\u70b9\u8f7d\u8377\uff0cFx");
    model.component("comp1").physics("beam").feature("pl4").selection().named("geom1_sel3");
    model.component("comp1").physics("beam").feature("pl4").set("forcePoint", new int[]{1, 0, 0});
    model.component("comp1").physics("beam").feature("pl4").set("loadGroup", "lg4");
    model.component("comp1").physics("beam").create("pl5", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl5").label("\u70b9\u8f7d\u8377\uff0cFy");
    model.component("comp1").physics("beam").feature("pl5").selection().named("geom1_sel3");
    model.component("comp1").physics("beam").feature("pl5").set("forcePoint", new int[]{0, 1, 0});
    model.component("comp1").physics("beam").feature("pl5").set("loadGroup", "lg5");
    model.component("comp1").physics("beam").create("pl6", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl6").label("\u70b9\u8f7d\u8377\uff0cFz");
    model.component("comp1").physics("beam").feature("pl6").selection().named("geom1_sel3");
    model.component("comp1").physics("beam").feature("pl6").set("forcePoint", new int[]{0, 0, 1});
    model.component("comp1").physics("beam").feature("pl6").set("loadGroup", "lg6");

    model.component("comp1").multiphysics().create("sbc1", "SolidBeamConnection3D", -1);
    model.component("comp1").multiphysics("sbc1").label("\u5b9e\u4f53-\u6881\u8fde\u63a5\uff0c\u8fc7\u6e21");
    model.component("comp1").multiphysics("sbc1").set("connectionSettings", "BeamPointsTransition");
    model.component("comp1").multiphysics("sbc1").set("selectionControl", true);
    model.component("comp1").multiphysics("sbc1").selection("bndPointSolidSelectionTran").set(136);
    model.component("comp1").multiphysics("sbc1").selection("bndPointBeamSelectionTran").set(179);
    model.component("comp1").multiphysics().create("sbc2", "SolidBeamConnection3D", -1);
    model.component("comp1").multiphysics("sbc2").label("\u5b9e\u4f53-\u6881\u8fde\u63a5\uff0c\u5e38\u89c4");
    model.component("comp1").multiphysics("sbc2").set("selectionControl", true);
    model.component("comp1").multiphysics("sbc2").selection("bndPointSolidSelection").set(68);
    model.component("comp1").multiphysics("sbc2").selection("bndPointBeamSelection").set(177);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 68, 69, 136);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.002);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 3, 4, 6);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "explicit");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("explicit", "range(0,0.015,1)");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(3, 6);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("type", "explicit");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2")
         .set("explicit", "range(0,0.015,0.92) range(0.92,0.005,1)");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(2, 5);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.002);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(309, 310);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").feature("stat").setSolveFor("/physics/beam", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/sbc2", false);
    model.study("std1").feature("stat").set("useinitsol", true);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").set("useloadcase", true);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 2);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 0, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 0, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u626d\u77e9", 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 1, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 1, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u5f2f\u77e9 Y", 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", true, 1, 1);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 2, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 2, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u5f2f\u77e9 Z", 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", true, 2, 2);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 3, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 3, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f74\u5411\u529b", 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", true, 3, 3);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 4, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 4, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u6a2a\u5411\u529b Y", 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", true, 4, 4);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 5);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 5);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 0);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 0);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 1);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 1);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 2);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 2);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 3);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 3);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 4);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 4);
    model.study("std1").feature("stat2").setIndex("loadgroup", false, 5, 5);
    model.study("std1").feature("stat2").setIndex("loadgroupweight", "1.0", 5, 5);
    model.study("std1").feature("stat2").setIndex("loadcase", "\u6a2a\u5411\u529b Z", 5);
    model.study("std1").feature("stat2").setIndex("loadgroup", true, 5, 5);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").set("control", "user");
    model.sol("sol1").feature("v2").set("control", "user");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scaleval", "1.0e-10");
    model.sol("sol1").feature("v2").feature("comp1_u2").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_u2").set("scaleval", "1.0e-10");
    model.sol("sol1").feature("v2").feature("comp1_sbc1_ww").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_sbc1_C0").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_sbc1_C1").set("solvefor", false);
    model.sol("sol1").feature("v2").feature("comp1_sbc1_C2").set("solvefor", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 6, 0);
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
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1beam", "Beam");
    model.result().dataset("dset1beam").set("data", "dset1");
    model.result().dataset("dset1beam").set("physicsinterface", "beam");
    model.result().dataset("dset1beam").set("refinement", 2);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1beam");
    model.result("pg2").setIndex("looplevel", 6, 0);
    model.result("pg2").label("\u5e94\u529b\uff0c\u4e09\u7ef4 (beam)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"beam.misesS"});
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"beam.uS", "beam.vS", "beam.wS"});
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff0c\u5b9e\u4f53\u548c\u6881");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("data", "dset1beam");
    model.result("pg1").run();
    model.result("pg1").feature().copy("surf1", "pg2/surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("inheritplot", "vol1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b\uff0c\u6881");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\u6bd4\u8f83\uff0c\u5b9e\u4f53");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").set("edges", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "(solid.mises-genext1(solid.mises))");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "solid.mises");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature("surf2").create("sel1", "Selection");
    model.result("pg3").feature("surf2").feature("sel1").selection()
         .set(69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136);
    model.result("pg3").run();
    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("geom1_sel2");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u7275\u5f15\u529b");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "solid.mises");
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"solid.Tax", "solid.Tay", "solid.Taz"});
    model.result("pg4").feature("arws1")
         .set("descr", "\u7275\u5f15\u529b\uff08\u529b/\u9762\u79ef\uff09 \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg4").feature("arws1").set("arrowcount", 1000);
    model.result("pg4").feature("arws1").set("inheritplot", "surf1");
    model.result("pg4").run();
    model.result("pg4").set("view", "new");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").run();
    model.result("pg4").feature("arws1").set("arrowcount", 120);
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u6881\u548c\u5b9e\u4f53\u8fde\u63a5");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u5b9e\u4f53-\u6881\u8fde\u63a5\u201d\u591a\u7269\u7406\u573a\u8026\u5408\u5728\u201c\u56fa\u4f53\u529b\u5b66\u201d\u4e0e\u201c\u6881\u201d\u63a5\u53e3\u4e4b\u95f4\u5efa\u7acb\u8fc7\u6e21\uff0c\u8ba8\u8bba\u4e86\u4e24\u79cd\u4e0d\u540c\u7684\u8fde\u63a5\u7c7b\u578b\uff0c\u5e76\u5bf9\u8fc7\u6e21\u533a\u57df\u7684\u5e94\u529b\u5206\u5e03\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("beam_solid_connection.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
