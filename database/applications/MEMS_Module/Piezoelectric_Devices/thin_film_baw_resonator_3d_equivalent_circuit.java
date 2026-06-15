/*
 * thin_film_baw_resonator_3d_equivalent_circuit.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:01 by COMSOL 6.3.0.290. */
public class thin_film_baw_resonator_3d_equivalent_circuit {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/es", true);
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().label("\u53c2\u6570 - \u6709\u9650\u5143\u6a21\u578b");
    model.param().set("L", "85[um]");
    model.param().descr("L", "\u8c10\u632f\u5668\u8fb9\u957f");
    model.param().set("L_a", "10[um]");
    model.param().descr("L_a", "\u951a\u7684\u957f\u5ea6");
    model.param().set("W_a", "10[um]");
    model.param().descr("W_a", "\u951a\u7684\u5bbd\u5ea6");
    model.param().set("t_te", "0.1[um]");
    model.param().descr("t_te", "\u9876\u90e8\u7535\u6781\u539a\u5ea6");
    model.param().set("t_piezo", "1[um]");
    model.param().descr("t_piezo", "\u538b\u7535\u5c42\u539a\u5ea6");
    model.param().set("t_be", "0.2[um]");
    model.param().descr("t_be", "\u5e95\u90e8\u7535\u6781\u539a\u5ea6");
    model.param().set("t_nitride", "0.2[um]");
    model.param().descr("t_nitride", "\u6c2e\u5316\u7269\u819c\u539a\u5ea6");
    model.param().set("Vapp", "5[V]");
    model.param().descr("Vapp", "\u5916\u52a0\u7535\u538b");
    model.param().set("L_apo", "L/(2*tan(36[deg]))");
    model.param().descr("L_apo", "\u4e94\u8fb9\u5f62\u7684\u4e2d\u5fc3\u8ddd");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 - \u7b49\u6548\u7535\u8def\u6a21\u578b");
    model.param("par2").set("Cm", "62[fF]");
    model.param("par2").descr("Cm", "\u7535\u5bb9\u5668\uff0c\u8fd0\u52a8");
    model.param("par2").set("Lm", "39[nH]");
    model.param("par2").descr("Lm", "\u7535\u611f\u5668\uff0c\u8fd0\u52a8");
    model.param("par2").set("Rm", "1[ohm]");
    model.param("par2").descr("Rm", "\u7535\u963b\u5668\uff0c\u8fd0\u52a8");
    model.param("par2").set("Co", "1[pF]");
    model.param("par2").descr("Co", "\u7535\u5bb9\u5668");
    model.param("par2").set("Ro", "500[ohm]");
    model.param("par2").descr("Ro", "\u7535\u963b\u5668");
    model.param("par2").set("Rs", "1[ohm]");
    model.param("par2").descr("Rs", "\u7535\u963b\u5668");
    model.param("par2").set("Vsrc", "5[V]");
    model.param("par2").descr("Vsrc", "\u7535\u538b\u6e90");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "L_apo");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("size", "2*L");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("rot", -36);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"W_a/2", "L_a"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "L_apo"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", 36);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input")
         .set("dif1", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "t_nitride", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "t_nitride+t_be", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "t_nitride+t_be+t_piezo", 2);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "t_nitride+t_be+t_piezo+t_te", 3);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0", "0"});
    model.component("comp1").geom("geom1").run("ext1");

    model.component("comp1").view("view1").camera().set("viewscaletype", "manual");
    model.component("comp1").view("view1").camera().set("zscale", 5);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u56fa\u5b9a");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1, 4, 7, 10);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6c2e\u5316\u7269");
    model.component("comp1").selection("sel2").set(1, 5);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u9876\u90e8\u7535\u6781");
    model.component("comp1").selection("sel3").set(4, 8);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5e95\u90e8\u7535\u6781");
    model.component("comp1").selection("sel4").set(2, 6);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u538b\u7535");
    model.component("comp1").selection("sel5").set(3, 7);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u5bf9\u79f0");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(2, 5, 8, 11, 19, 22, 25, 28, 35, 36, 37, 38);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(30);

    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 3);
    model.component("comp1").physics("es").feature("ccns1").selection().set(1, 2, 5, 6);
    model.component("comp1").physics("es").feature("ccnp1").selection().named("sel5");
    model.component("comp1").physics("es").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("es").feature("symp1").selection().named("sel6");
    model.component("comp1").physics("es").create("term1", "DomainTerminal", 3);
    model.component("comp1").physics("es").feature("term1").label("\u9876\u90e8\u7ec8\u7aef");
    model.component("comp1").physics("es").feature("term1").selection().named("sel3");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", "Vapp");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(9, 26);
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("pzm1").selection().named("sel5");
    model.component("comp1").physics("solid").feature("pzm1").create("mdmp1", "MechanicalDamping", 3);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel1");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("sel6");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(12, 29);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("sel5");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis4").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis4").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat1").label("Aluminum Nitride");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.1);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"9", "0", "0", "0", "9", "0", "0", "0", "9"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "3300[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("sE", new String[]{"2.8987e-12[1/Pa]", "-9.326e-013[1/Pa]", "-5.0038e-013[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-9.326e-013[1/Pa]", "2.8987e-12[1/Pa]", "-5.0038e-013[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-5.0038e-013[1/Pa]", "-5.0038e-013[1/Pa]", "2.8253e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "8E-12[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "8E-12[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "7.6628E-12[1/Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-1.9159e-012[C/N]", "0[C/N]", "0[C/N]", "-1.9159e-012[C/N]", "0[C/N]", "0[C/N]", "4.9597e-012[C/N]", "0[C/N]", 
         "-3.84e-012[C/N]", "0[C/N]", "-3.84e-012[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"9.2081", "0", "0", "0", "9.2081", "0", "0", "0", "10.1192"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("cE", new String[]{"4.1e+011[Pa]", "1.49e+011[Pa]", "0.99e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.49e+011[Pa]", "4.1e+011[Pa]", "0.99e+011[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0.99e+011[Pa]", "0.99e+011[Pa]", "3.89e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "1.25e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.25e+011[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.305e+011[Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-0.58[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-0.58[C/m^2]", "0[C/m^2]", "0[C/m^2]", "1.55[C/m^2]", "0[C/m^2]", 
         "-0.48[C/m^2]", "0[C/m^2]", "-0.48[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"9", "0", "0", "0", "9", "0", "0", "0", "9"});
    model.component("comp1").material("mat1").selection().named("sel5");
    model.component("comp1").material("mat1").propertyGroup("StressCharge").set("eta_cE", new String[]{"1e-3"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Al - Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"35.5e6[S/m]", "0", "0", "0", "35.5e6[S/m]", "0", "0", "0", "35.5e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.1e-6[1/K]", "0", "0", "0", "23.1e-6[1/K]", "0", "0", "0", "23.1e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "904[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"237[W/(m*K)]", "0", "0", "0", "237[W/(m*K)]", "0", "0", "0", "237[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70.0e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").selection().named("sel3");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", new String[]{"1e-4"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat3").label("Molybdenum");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("fresnel", 0.3);
    model.component("comp1").material("mat3").set("roughness", 0.1);
    model.component("comp1").material("mat3").set("diffusewrap", 0);
    model.component("comp1").material("mat3").set("reflectance", 0);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"4.8e-6[1/K]", "0", "0", "0", "4.8e-6[1/K]", "0", "0", "0", "4.8e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "10200[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "250[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"138[W/(m*K)]", "0", "0", "0", "138[W/(m*K)]", "0", "0", "0", "138[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "312[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.31");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("l", "-300[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("m", "-850[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("n", "-910[GPa]");
    model.component("comp1").material("mat3").selection().named("sel4");
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("lossfactor", new String[]{"1e-4"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").label("Si3N4 - Silicon nitride");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.3e-6[1/K]", "0", "0", "0", "2.3e-6[1/K]", "0", "0", "0", "2.3e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"9.7", "0", "0", "0", "9.7", "0", "0", "0", "9.7"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "3100[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"20[W/(m*K)]", "0", "0", "0", "20[W/(m*K)]", "0", "0", "0", "20[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "250e9[Pa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.23");
    model.component("comp1").material("mat4").selection().named("sel2");
    model.component("comp1").material("mat4").propertyGroup("def").set("lossfactor", new String[]{"1e-2"});

    model.study("std1").label("\u9891\u57df - FEM");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("punit", "GHz");
    model.study("std1").feature("freq").set("plist", "range(2.8, 0.01, 3.8)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u9891\u7387\u54cd\u5e94");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "Log10(Abs(I))");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").label("FEM");
    model.result("pg1").feature("glob1").setIndex("expr", "log10(abs(10*es.I0_1))", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "FEM", 0);
    model.result("pg1").run();

    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/cir", true);

    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "Vsrc");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").label("R_s");
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "Rs");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").label("R_m");
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "Rm");
    model.component("comp1").physics("cir").create("L1", "Inductor", -1);
    model.component("comp1").physics("cir").feature("L1").label("L_m");
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 4, 1, 0);
    model.component("comp1").physics("cir").feature("L1").set("L", "Lm");
    model.component("comp1").physics("cir").create("C1", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("C1").label("C_m");
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 4, 0, 0);
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("C1").set("C", "Cm");
    model.component("comp1").physics("cir").create("R3", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R3").label("R_o");
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("R3").set("R", "Ro");
    model.component("comp1").physics("cir").create("C2", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("C2").label("C_o");
    model.component("comp1").physics("cir").feature("C2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("C2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("C2").set("C", "Co");

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/es", true);
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/pze1", true);
    model.study("std2").label("\u9891\u57df - \u7b49\u6548\u7535\u8def");
    model.study("std2").feature("freq").set("punit", "GHz");
    model.study("std2").feature("freq").set("plist", "range(2.8,0.01,3.8)");
    model.study("std2").feature("freq").setSolveFor("/physics/es", false);
    model.study("std2").feature("freq").setSolveFor("/physics/solid", false);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/pze1", false);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").label("\u7b49\u6548\u7535\u8def\uff0c\u521d\u59cb\u503c");
    model.result("pg1").feature("glob2").set("data", "dset2");
    model.result("pg1").feature("glob2").setIndex("expr", "log10(abs(cir.R1.i))", 0);
    model.result("pg1").feature("glob2").setIndex("descr", "\u7b49\u6548\u7535\u8def\uff0c\u521d\u59cb\u503c", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("glob1").createTable("tbl1");
    model.result().table("tbl1").label("FEM \u53c2\u8003\u6570\u636e");

    model.study().create("std3");
    model.study("std3").label("\u53c2\u6570\u63d0\u53d6");
    model.study("std3").feature().copy("freq", "std2/freq");
    model.study("std3").create("lsqo", "LSQOptimization");
    model.study("std3").feature("lsqo").set("source", "resultTable");
    model.study("std3").feature("lsqo").setEntry("columnType", "col1", "freq");
    model.study("std3").feature("lsqo").setEntry("frequencyUnit", "col1", "GHz");
    model.study("std3").feature("lsqo").setEntry("modelExpression", "col2", "log10(abs(comp1.cir.R1.i))");
    model.study("std3").feature("lsqo").setEntry("unit", "col2", "1");
    model.study("std3").feature("lsqo").setEntry("scaleMethod", "col2", "manual");
    model.study("std3").feature("lsqo").setIndex("pname", "Cm", 0);
    model.study("std3").feature("lsqo").setIndex("initval", "62[fF]", 0);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std3").feature("lsqo").setIndex("pname", "Cm", 0);
    model.study("std3").feature("lsqo").setIndex("initval", "62[fF]", 0);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std3").feature("lsqo").setIndex("pname", "Co", 1);
    model.study("std3").feature("lsqo").setIndex("initval", "1[pF]", 1);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std3").feature("lsqo").setIndex("pname", "Co", 1);
    model.study("std3").feature("lsqo").setIndex("initval", "1[pF]", 1);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std3").feature("lsqo").setIndex("pname", "L", 2);
    model.study("std3").feature("lsqo").setIndex("initval", "85[um]", 2);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std3").feature("lsqo").setIndex("pname", "L", 2);
    model.study("std3").feature("lsqo").setIndex("initval", "85[um]", 2);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std3").feature("lsqo").setIndex("pname", "L_a", 3);
    model.study("std3").feature("lsqo").setIndex("initval", "10[um]", 3);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std3").feature("lsqo").setIndex("pname", "L_a", 3);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std3").feature("lsqo").setIndex("initval", "10[um]", 3);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std3").feature("lsqo").setIndex("pname", "L_apo", 4);
    model.study("std3").feature("lsqo").setIndex("initval", "L/(2*tan(36[deg]))", 4);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 4);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 4);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 4);
    model.study("std3").feature("lsqo").setIndex("pname", "L_apo", 4);
    model.study("std3").feature("lsqo").setIndex("initval", "L/(2*tan(36[deg]))", 4);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 4);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 4);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 4);
    model.study("std3").feature("lsqo").setIndex("pname", "Lm", 5);
    model.study("std3").feature("lsqo").setIndex("initval", "39[nH]", 5);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 5);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 5);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 5);
    model.study("std3").feature("lsqo").setIndex("pname", "Lm", 5);
    model.study("std3").feature("lsqo").setIndex("initval", "39[nH]", 5);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 5);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 5);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 5);
    model.study("std3").feature("lsqo").setIndex("scale", "60[fF]", 0);
    model.study("std3").feature("lsqo").setIndex("lbound", "45[fF]", 0);
    model.study("std3").feature("lsqo").setIndex("ubound", "75[fF]", 0);
    model.study("std3").feature("lsqo").setIndex("pname", "", 1);
    model.study("std3").feature("lsqo").setIndex("scale", "40[nH]", 1);
    model.study("std3").feature("lsqo").setIndex("lbound", "10[nH]", 1);
    model.study("std3").feature("lsqo").setIndex("ubound", "70[nH]", 1);
    model.study("std3").feature("lsqo").setIndex("pname", "Co", 2);
    model.study("std3").feature("lsqo").setIndex("scale", "1[pF]", 2);
    model.study("std3").feature("lsqo").setIndex("lbound", "0.1[pF]", 2);
    model.study("std3").feature("lsqo").setIndex("ubound", "2[pF]", 2);
    model.study("std3").feature("lsqo").setIndex("pname", "Rs", 3);
    model.study("std3").feature("lsqo").setIndex("scale", "10[ohm]", 3);
    model.study("std3").feature("lsqo").setIndex("lbound", "0.001[ohm]", 3);
    model.study("std3").feature("lsqo").setIndex("ubound", "20[ohm]", 3);
    model.study("std3").feature("lsqo").setIndex("pname", "Rm", 4);
    model.study("std3").feature("lsqo").setIndex("scale", "10[ohm]", 4);
    model.study("std3").feature("lsqo").setIndex("lbound", "0.01[ohm]", 4);
    model.study("std3").feature("lsqo").setIndex("ubound", "20[ohm]", 4);
    model.study("std3").feature("lsqo").setIndex("pname", "Ro", 5);
    model.study("std3").feature("lsqo").setIndex("pname", "Lm", 1);
    model.study("std3").feature("lsqo").setIndex("scale", "2500[ohm]", 5);
    model.study("std3").feature("lsqo").setIndex("lbound", "100[ohm]", 5);
    model.study("std3").feature("lsqo").setIndex("ubound", "5000[ohm]", 5);
    model.study("std3").feature("lsqo").set("optsolver", "snopt");
    model.study("std3").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("st1").set("splitcomplex", true);

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u53c2\u6570\u4f30\u8ba1");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u9891\u7387");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").label("FEM (\u6a21\u578b)");
    model.result("pg2").feature("glob1").set("expr", new String[]{"opt.glsobj.col2.model"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"FEM (\u6a21\u578b)"});
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").label("FEM (\u6570\u636e)");
    model.result("pg2").feature("glob2").set("expr", new String[]{"opt.glsobj.col2.data"});
    model.result("pg2").feature("glob2").set("descr", new String[]{"FEM (\u6570\u636e)"});
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "freq");
    model.result("pg2").feature("glob2").set("linestyle", "none");
    model.result("pg2").feature("glob2").set("linemarker", "point");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg2").run();

    model.study("std3").feature("lsqo").set("probewindow", "");
    model.study().create("std4");
    model.study("std4").create("eig", "Eigenfrequency");
    model.study("std4").feature("eig").set("plotgroup", "Default");
    model.study("std4").feature("eig").set("shift", "1[Hz]");
    model.study("std4").feature("eig").set("chkeigregion", true);
    model.study("std4").feature("eig").set("storefact", false);
    model.study("std4").feature("eig").set("linpsolnum", "auto");
    model.study("std4").feature("eig").set("solnum", "auto");
    model.study("std4").feature("eig").set("notsolnum", "auto");
    model.study("std4").feature("eig").set("outputmap", new String[]{});
    model.study("std4").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").setSolveFor("/physics/es", true);
    model.study("std4").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std4").feature("eig").setSolveFor("/physics/cir", true);
    model.study("std4").feature("eig").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.study("std4").feature("eig").set("neigsactive", true);
    model.study("std4").feature("eig").set("neigs", 25);
    model.study("std4").feature("eig").set("eigunit", "GHz");
    model.study("std4").feature("eig").set("shift", "3.25");
    model.study("std4").feature("eig").setSolveFor("/physics/cir", false);
    model.study("std4").label("\u7279\u5f81\u9891\u7387");
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("data", "dset4");
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("data", "mir1");
    model.result().dataset("sec1").set("sectors", 5);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u6a21\u5f0f");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "solid.disp");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").run();
    model.result("pg3").set("data", "sec1");
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{17});
    model.result("pg3").run();
    model.result("pg3").set("showlegends", false);

    model.view("view3").set("showgrid", false);
    model.view("view3").set("showaxisorientation", false);

    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg3").feature("surf1").set("descractive", false);
    model.result("pg3").feature("surf1").set("expr", "w");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortable", "RainbowClassic");
    model.result("pg3").feature("surf1").set("expr", "solid.disp");
    model.result("pg3").run();

    model.view("view3").set("showaxisorientation", true);
    model.view("view3").set("showgrid", true);

    model.result("pg3").set("showlegends", true);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb\u76f8\u4f4d\u5747\u5300\u6027");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "abs(intop1(w))/intop1(abs(w))", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u4f4d\u79fb\u76f8\u4f4d\u5747\u5300\u6027", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "freq");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("linestyle", "none");
    model.result("pg4").feature("glob1").set("linemarker", "point");

    model.title("\u5177\u6709\u7b49\u6548\u7535\u8def\u7684\u8584\u819c\u4f53\u58f0\u6ce2\u8c10\u632f\u5668");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u53c2\u6570\u4f30\u8ba1\u201d\u7814\u7a76\u63a8\u5bfc\u51fa MEMS \u8c10\u632f\u5668\u7684\u7535\u8def\u6a21\u578b\uff0c\u8fd9\u662f\u4f7f\u7528\u201c\u7535\u8def\u201d\u63a5\u53e3\u521b\u5efa\u7684\u7ecf\u8fc7\u4fee\u6539\u7684 Butterworth-Van Dyke \u7535\u8def\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u53c2\u6570\u4f30\u8ba1\u201d\u7814\u7a76\u6c42\u89e3\u7535\u8def\u6a21\u578b\u4e2d\u79bb\u6563\u5143\u4ef6\u7684\u53c2\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("thin_film_baw_resonator_3d_equivalent_circuit.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
