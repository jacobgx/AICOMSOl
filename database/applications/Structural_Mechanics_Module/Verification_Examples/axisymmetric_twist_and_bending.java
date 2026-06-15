/*
 * axisymmetric_twist_and_bending.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:22 by COMSOL 6.3.0.290. */
public class axisymmetric_twist_and_bending {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("r0", "2[mm]");
    model.param().descr("r0", "\u5185\u534a\u5f84");
    model.param().set("R1", "12[mm]");
    model.param().descr("R1", "\u5916\u534a\u5f84\uff0c\u539a\u90e8\u5206");
    model.param().set("R2", "5[mm]");
    model.param().descr("R2", "\u5916\u534a\u5f84\u8584\u90e8\u5206");
    model.param().set("L1", "18[mm]");
    model.param().descr("L1", "\u957f\u5ea6\uff0c\u539a\u90e8\u5206");
    model.param().set("L2", "50[mm]");
    model.param().descr("L2", "\u957f\u5ea6\uff0c\u8584\u90e8\u5206");
    model.param().set("I", "(pi/4)*(R2^4 - r0^4)");
    model.param().descr("I", "\u9762\u79ef\u60ef\u6027\u77e9");
    model.param().set("J", "2*I");
    model.param().descr("J", "\u626d\u8f6c\u5e38\u6570");
    model.param().set("A_load", "pi*(R2^2 - r0^2)");
    model.param().descr("A_load", "\u5916\u52a0\u8f7d\u8377\u7684\u9762\u79ef");
    model.param().set("F_load", "10[MPa]*A_load");
    model.param().descr("F_load", "\u603b\u4f5c\u7528\u529b");
    model.param().set("Mt", "(2*pi/3)*(F_load/A_load)*(R2^3 - r0^3)");
    model.param().descr("Mt", "\u7b49\u91cf\u626d\u77e9");
    model.param().set("Mb", "F_load*(L2 - L1)");
    model.param().descr("Mb", "\u8fc7\u6e21\u671f\u7684\u5f2f\u77e9");

    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("\u8f74\u622a\u9762");
    model.geom("part1").lengthUnit("mm");
    model.geom("part1").create("pol1", "Polygon");
    model.geom("part1").feature("pol1").set("source", "table");
    model.geom("part1").feature("pol1").setIndex("table", "r0", 0, 0);
    model.geom("part1").feature("pol1").setIndex("table", 0, 0, 1);
    model.geom("part1").feature("pol1").setIndex("table", "R1", 1, 0);
    model.geom("part1").feature("pol1").setIndex("table", 0, 1, 1);
    model.geom("part1").feature("pol1").setIndex("table", "R1", 2, 0);
    model.geom("part1").feature("pol1").setIndex("table", "L1", 2, 1);
    model.geom("part1").feature("pol1").setIndex("table", "R2", 3, 0);
    model.geom("part1").feature("pol1").setIndex("table", "L1", 3, 1);
    model.geom("part1").feature("pol1").setIndex("table", "R2", 4, 0);
    model.geom("part1").feature("pol1").setIndex("table", "L2", 4, 1);
    model.geom("part1").feature("pol1").setIndex("table", "r0", 5, 0);
    model.geom("part1").feature("pol1").setIndex("table", "L2", 5, 1);
    model.geom("part1").run("pol1");
    model.geom("part1").create("fil1", "Fillet");
    model.geom("part1").feature("fil1").selection("pointinsketch").set("pol1", 3);
    model.geom("part1").feature("fil1").set("radius", 1);
    model.geom("part1").run("fil1");
    model.geom("part1").create("fil2", "Fillet");
    model.geom("part1").feature("fil2").selection("pointinsketch").set("fil1", 3);
    model.geom("part1").feature("fil2").set("radius", 2);
    model.geom("part1").run("fil2");

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

    model.group().create("lg1", "LoadGroup");
    model.group("lg1").label("\u8f74\u5411\u62c9\u4f38");
    model.group().create("lg2", "LoadGroup");
    model.group("lg2").label("\u626d\u8f6c");
    model.group().create("lg3", "LoadGroup");
    model.group("lg3").label("\u5f2f\u66f2");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").physics("solid").prop("Mode2Daxi").set("includeTwist", true);
    model.component("comp1").physics("solid").prop("Mode2Daxi").set("ModeExtension", true);
    model.component("comp1").physics("solid").prop("Mode2Daxi").set("mk", 1);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1")
         .label("\u8fb9\u754c\u8f7d\u8377\uff1a\u8f74\u5411\u5ef6\u4f38");
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new String[]{"0", "0", "F_load"});
    model.component("comp1").physics("solid").feature("bndl1").set("loadGroup", "lg1");
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl2").label("\u8fb9\u754c\u8f7d\u8377\uff1a\u626d\u8f6c");
    model.component("comp1").physics("solid").feature("bndl2").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl2").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl2").set("force", new String[]{"0", "F_load", "0"});
    model.component("comp1").physics("solid").feature("bndl2").set("loadGroup", "lg2");
    model.component("comp1").physics("solid").create("bndl3", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl3").label("\u8fb9\u754c\u8f7d\u8377\uff1a\u5f2f\u66f2");
    model.component("comp1").physics("solid").feature("bndl3").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl3").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl3")
         .set("force", new String[]{"F_load", "-i*F_load", "0"});
    model.component("comp1").physics("solid").feature("bndl3").set("loadGroup", "lg3");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").set("opname", "max2Daxi");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("maxop1").selection().set(4, 5, 7);

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").set("quickplane", "xz");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("pi1", "PartInstance");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("pi1").set("part", "part1");
    model.component("comp2").geom("geom2").run("wp1");
    model.component("comp2").geom("geom2").feature().create("rev1", "Revolve");
    model.component("comp2").geom("geom2").feature("rev1").set("workplane", "wp1");
    model.component("comp2").geom("geom2").feature("rev1").selection("input").set("wp1");
    model.component("comp2").geom("geom2").feature("rev1").set("angtype", "full");
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").coordSystem().create("sys3", "Cylindrical");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").coordSystem("sys3")
         .label("\u67f1\u5750\u6807\u7cfb\uff08\u6750\u6599\u5750\u6807\u7cfb\uff09");
    model.component("comp2").coordSystem("sys3").set("frametype", "material");

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/solid2", true);

    model.component("comp2").material().create("matlnk2", "Link");

    model.component("comp2").physics("solid2").create("fix1", "Fixed", 2);
    model.component("comp2").physics("solid2").feature("fix1").selection().set(3, 4, 18, 26);
    model.component("comp2").physics("solid2").create("bndl1", "BoundaryLoad", 2);
    model.component("comp2").physics("solid2").feature("bndl1")
         .label("\u8fb9\u754c\u8f7d\u8377\uff1a\u8f74\u5411\u5ef6\u4f38");
    model.component("comp2").physics("solid2").feature("bndl1").selection().set(13, 14, 23, 27);
    model.component("comp2").physics("solid2").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp2").physics("solid2").feature("bndl1").set("force", new String[]{"0", "0", "F_load"});
    model.component("comp2").physics("solid2").feature("bndl1").set("loadGroup", "lg1");
    model.component("comp2").physics("solid2").create("bndl2", "BoundaryLoad", 2);
    model.component("comp2").physics("solid2").feature("bndl2").label("\u8fb9\u754c\u8f7d\u8377\uff1a\u626d\u8f6c");
    model.component("comp2").physics("solid2").feature("bndl2").selection().set(13, 14, 23, 27);
    model.component("comp2").physics("solid2").feature("bndl2").set("coordinateSystem", "sys3");
    model.component("comp2").physics("solid2").feature("bndl2").set("forceType", "TotalForce");
    model.component("comp2").physics("solid2").feature("bndl2").set("force", new String[]{"0", "F_load", "0"});
    model.component("comp2").physics("solid2").feature("bndl2").set("loadGroup", "lg2");
    model.component("comp2").physics("solid2").create("bndl3", "BoundaryLoad", 2);
    model.component("comp2").physics("solid2").feature("bndl3").label("\u8fb9\u754c\u8f7d\u8377\uff1a\u5f2f\u66f2");
    model.component("comp2").physics("solid2").feature("bndl3").selection().set(13, 14, 23, 27);
    model.component("comp2").physics("solid2").feature("bndl3").set("forceType", "TotalForce");
    model.component("comp2").physics("solid2").feature("bndl3").set("force", new String[]{"F_load", "0", "0"});
    model.component("comp2").physics("solid2").feature("bndl3").set("loadGroup", "lg3");
    model.component("comp2").physics("solid2").create("lsr1", "LocalSystemResults", 3);
    model.component("comp2").physics("solid2").feature("lsr1").set("coordinateSystem", "sys3");

    model.component("comp2").cpl().create("maxop2", "Maximum");
    model.component("comp2").cpl("maxop2").selection().geom("geom2", 2);
    model.component("comp2").cpl("maxop2").set("opname", "max3D");
    model.component("comp2").cpl("maxop2").selection().set(7, 8, 9, 10, 11, 12, 20, 21, 22, 28, 29, 30);

    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().set(33);
    model.component("comp2").mesh("mesh2").feature("ftri1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("dis1").selection().set(61);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("dis1").set("numelem", 20);
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("size").set("hauto", 2);
    model.component("comp2").mesh("mesh2").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u8f74\u5411\u62c9\u4f38\u548c\u626d\u8f6c");
    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 2);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 2);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f74\u5411\u62c9\u4f38", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 2);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 2);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 2);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 2);
    model.study("std1").feature("stat").setIndex("loadcase", "\u626d\u8f6c", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").label("\u5e94\u529b (solid2)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/physics/solid2", true);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").label("\u7814\u7a76 2\uff1a\u5f2f\u66f2");
    model.study("std2").feature("freq").set("plist", 0);
    model.study("std2").feature("freq").set("useloadcase", true);
    model.study("std2").feature("freq").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std2").feature("freq").setIndex("loadgroup", false, 0, 0);
    model.study("std2").feature("freq").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std2").feature("freq").setIndex("loadgroup", false, 0, 1);
    model.study("std2").feature("freq").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std2").feature("freq").setIndex("loadgroup", false, 0, 2);
    model.study("std2").feature("freq").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("std2").feature("freq").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std2").feature("freq").setIndex("loadgroup", false, 0, 0);
    model.study("std2").feature("freq").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std2").feature("freq").setIndex("loadgroup", false, 0, 1);
    model.study("std2").feature("freq").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std2").feature("freq").setIndex("loadgroup", false, 0, 2);
    model.study("std2").feature("freq").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("std2").feature("freq").setIndex("loadcase", "\u5f2f\u66f2", 0);
    model.study("std2").feature("freq").setIndex("loadgroup", true, 0, 2);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").label("\u5e94\u529b (solid) 1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset3solidrev", "Revolve2D");
    model.result().dataset("dset3solidrev").set("data", "dset3");
    model.result().dataset("dset3solidrev").set("revangle", 225);
    model.result().dataset("dset3solidrev").set("startangle", -90);
    model.result().dataset("dset3solidrev").set("hasspacevars", true);
    model.result().dataset("dset3solidrev").set("modenumber", "solid.mk");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset3solidrev");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").setIndex("looplevel", 1, 1);
    model.result("pg5").label("\u5e94\u529b, 3D (solid) 1");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result().dataset("dset3solidrev").set("hasspacevars", true);
    model.result("pg5").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").feature("surf1").feature("def").set("descractive", true);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").setIndex("looplevel", 1, 1);
    model.result("pg6").label("\u5e94\u529b (solid2) 1");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", new String[]{"solid2.misesGp_peak"});
    model.result("pg6").feature("vol1").set("threshold", "manual");
    model.result("pg6").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("vol1").set("colortable", "Rainbow");
    model.result("pg6").feature("vol1").set("colortabletrans", "none");
    model.result("pg6").feature("vol1").set("colorscalemode", "linear");
    model.result("pg6").feature("vol1").set("resolution", "custom");
    model.result("pg6").feature("vol1").set("refine", 2);
    model.result("pg6").feature("vol1").set("colortable", "Prism");
    model.result("pg6").feature("vol1").create("def", "Deform");
    model.result("pg6").feature("vol1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg6").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2")
         .label("\u5e94\u529b\uff1a\u8f74\u5411\u62c9\u4f38\u548c\u626d\u8f6c\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u548c\u4e09\u7ef4\uff09");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("data", "dset2");
    model.result("pg2").feature("surf2").set("solutionparams", "parent");
    model.result("pg2").feature("surf2").set("expr", "solid2.mises");
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").feature("surf2").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf2").create("trn1", "Transformation");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("trn1").set("move", new String[]{"3*R1", "0", "0"});
    model.result("pg2").feature("surf2").feature("trn1").set("applytodatasetedges", false);
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("data", "dset2");
    model.result("pg2").feature("line1").set("expr", "1");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "black");
    model.result("pg2").feature("line1").create("trn1", "Transformation");
    model.result("pg2").run();
    model.result("pg2").feature("line1").feature("trn1").set("move", new String[]{"3*R1", "0", "0"});
    model.result("pg2").run();
    model.result("pg2").feature("line1").feature("trn1").set("applytodatasetedges", false);
    model.result("pg2").run();
    model.result("pg2").stepFirst(0);
    model.result("pg2").run();
    model.result("pg2").stepNext(0);
    model.result("pg2").run();
    model.result("pg5").run();
    model.result("pg5")
         .label("\u5e94\u529b\uff1a\u5f2f\u66f2\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u548c\u4e09\u7ef4\uff09");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "solid.mises");
    model.result("pg5").run();
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("data", "dset4");
    model.result("pg5").feature("surf2").set("expr", "solid2.mises");
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature("surf2").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf2").create("trn1", "Transformation");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("trn1").set("move", new String[]{"3*R1", "0", "0"});
    model.result("pg5").feature("surf2").feature("trn1").set("applytodatasetedges", false);
    model.result("pg5").run();
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("data", "dset4");
    model.result("pg5").feature("line1").set("expr", "1");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "black");
    model.result("pg5").feature("line1").create("trn1", "Transformation");
    model.result("pg5").run();
    model.result("pg5").feature("line1").feature("trn1").set("applytodatasetedges", false);
    model.result("pg5").feature("line1").feature("trn1").set("move", new String[]{"3*R1", "0", "0"});
    model.result("pg5").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u5e94\u529b\u96c6\u4e2d\u7cfb\u6570");
    model.result().evaluationGroup("eg1").set("data", "none");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset1");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "max2Daxi(solid.szz)/(F_load/A_load)", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u8f74\u5411\u62c9\u4f38\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\uff09", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "comp2.max3D(comp2.solid2.lsr1.sl33)/(F_load/A_load)", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u8f74\u5411\u62c9\u4f38\uff08\u4e09\u7ef4\uff09", 1);
    model.result().evaluationGroup("eg1").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev2").set("data", "dset1");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "max2Daxi(solid.sphiz)/(Mt*R2/J)", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("unit", "", 0);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("descr", "\u626d\u8f6c\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\uff09", 0);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("expr", "comp2.max3D(solid2.lsr1.sl23)/(Mt*R2/J)", 1);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("descr", "\u626d\u8f6c\uff08\u4e09\u7ef4\uff09", 1);
    model.result().evaluationGroup("eg1").create("gev3", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev3").set("data", "dset3");
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "max2Daxi(abs(solid.szz))/(Mb*R2/I)", 0);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("descr", "\u5f2f\u66f2\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\uff09", 0);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("expr", "comp2.max3D(solid2.lsr1.sl33)/(Mb*R2/I)", 1);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("descr", "\u5f2f\u66f2\uff08\u4e09\u7ef4\uff09", 1);
    model.result().evaluationGroup("eg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "solid.mises");
    model.result("pg4").run();
    model.result("pg6").run();
    model.result("pg6").feature("vol1").set("expr", "solid2.mises");
    model.result("pg6").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", "R2");
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", "L2");
    model.result("pg2").run();
    model.result().duplicate("pg7", "pg2");
    model.result("pg7").run();
    model.result("pg7").label("\u7f29\u7565\u56fe");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("expr", "1");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature().remove("def1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf2").feature("mtrl1").set("family", "steel");
    model.result("pg7").run();
    model.result("pg7").feature().remove("line1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("arpt1", "ArrowPoint");
    model.result("pg7").feature("arpt1").set("revcoordsys", "cylindrical");
    model.result("pg7").feature("arpt1").set("data", "cpt1");
    model.result("pg7").feature("arpt1").set("expr", new String[]{"0", "0", "r0*5"});
    model.result("pg7").feature("arpt1").set("arrowtype", "double");
    model.result("pg7").feature("arpt1").set("scaleactive", true);
    model.result("pg7").feature("arpt1").create("trn1", "Transformation");
    model.result("pg7").run();
    model.result("pg7").feature("arpt1").feature("trn1").set("move", new String[]{"-R2", "0", "-1*r0"});
    model.result("pg7").feature("arpt1").feature("trn1").set("applytodatasetedges", false);
    model.result("pg7").run();

    model.view("view5").set("showgrid", false);
    model.view("view5").set("showaxisorientation", false);
    model.view("view5").set("environmentmap", "envmap_machineshop2");
    model.view("view5").set("shadowmapping", true);
    model.view("view5").set("displayoutput", true);
    model.view("view5").set("showgrid", true);
    model.view("view5").set("showaxisorientation", true);
    model.view("view5").set("shadowmapping", false);
    model.view("view5").set("displayoutput", false);

    model.result("pg7").run();
    model.result().remove("pg7");
    model.result().dataset().remove("cpt1");
    model.result("pg2").run();

    model.title("\u8f74\u5bf9\u79f0\u626d\u8f6c\u548c\u5f2f\u66f2");

    model
         .description("\u5728\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u201c\u56fa\u4f53\u529b\u5b66\u201d\u63a5\u53e3\u4e2d\u5305\u542b\u5468\u5411\u4f4d\u79fb\u53ef\u4ee5\u8ba1\u7b97\u626d\u8f6c\u548c\u5f2f\u66f2\u53d8\u5f62\u3002\u672c\u6a21\u578b\u4f7f\u7528\u8ba1\u7b97\u65b9\u4fbf\u7684\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u516c\u5f0f\u6765\u786e\u5b9a\u7a7a\u5fc3\u8f74\u5728\u8f74\u5411\u62c9\u4f38\u3001\u626d\u8f6c\u548c\u5f2f\u66f2\u8f7d\u8377\u5de5\u51b5\u4e0b\u7684\u5e94\u529b\u96c6\u4e2d\u7cfb\u6570\u3002\u4e3a\u4e86\u8bc1\u660e\u8fd9\u4e9b\u7cfb\u6570\u7684\u7b49\u6548\u6027\uff0c\u5176\u4e2d\u5c06\u6a21\u578b\u8bbe\u7f6e\u548c\u7ed3\u679c\u4e0e\u5168\u4e09\u7ef4\u5206\u6790\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("axisymmetric_twist_and_bending.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
