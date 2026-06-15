/*
 * micromechanical_model_of_a_piezoelectric_composite.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:13 by COMSOL 6.3.0.290. */
public class micromechanical_model_of_a_piezoelectric_composite {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Material_Models");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.param().set("v_f", "0.5");
    model.param().descr("v_f", "\u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.param().set("W", "2[m]");
    model.param().descr("W", "\u57fa\u672c\u5355\u5143\u7684\u5bbd\u5ea6");
    model.param().set("D", "2[m]");
    model.param().descr("D", "\u57fa\u672c\u5355\u5143\u7684\u6df1\u5ea6");
    model.param().set("H", "0.2[m]");
    model.param().descr("H", "\u57fa\u672c\u5355\u5143\u7684\u9ad8\u5ea6");
    model.param().set("V", "W*D*H");
    model.param().descr("V", "\u57fa\u672c\u5355\u5143\u7684\u4f53\u79ef");
    model.param().set("V_f", "v_f*V");
    model.param().descr("V_f", "\u7ea4\u7ef4\u4f53\u79ef");
    model.param().set("r_f", "sqrt(V_f/(2*H*pi))");
    model.param().descr("r_f", "\u5706\u67f1\u578b\u7ea4\u7ef4\u7684\u534a\u5f84");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"W", "D", "H"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "r_f");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new String[]{"0", "D/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "r_f");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("pos", new String[]{"W", "D/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("rot", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("r", "r_f");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("pos", new String[]{"W/2", "D"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c4").set("r", "r_f");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c4").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c4").set("pos", new String[]{"W/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c4");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "H", 0);
    model.component("comp1").geom("geom1").feature("ext1").label("\u7ea4\u7ef4");
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext1").set("color", "custom");
    model.component("comp1").geom("geom1").feature("ext1")
         .set("customcolor", new double[]{1, 0.501960813999176, 0.7529411911964417});
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").label("\u57fa\u4f53");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("ext1");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("dif1").set("color", "custom");
    model.component("comp1").geom("geom1").feature("dif1")
         .set("customcolor", new double[]{0.5882353186607361, 0.9411764740943909, 0.9411764740943909});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5bf9 1\uff0c\u6e90");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u5bf9 1\uff0c\u76ee\u6807");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "W");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u5bf9 2\uff0c\u6e90");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", 0);
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u5bf9 2\uff0c\u76ee\u6807");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "D");
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("boxsel5", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel5").label("\u5bf9 3\uff0c\u6e90");
    model.component("comp1").geom("geom1").feature("boxsel5").set("zmax", 0);
    model.component("comp1").geom("geom1").feature("boxsel5").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel5");
    model.component("comp1").geom("geom1").create("boxsel6", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel6").label("\u5bf9 3\uff0c\u76ee\u6807");
    model.component("comp1").geom("geom1").feature("boxsel6").set("zmin", "H");
    model.component("comp1").geom("geom1").feature("boxsel6").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel6");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"boxsel1", "boxsel2"});
    model.component("comp1").geom("geom1").feature("unisel1").label("\u5bf9 1");
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"boxsel3", "boxsel4"});
    model.component("comp1").geom("geom1").feature("unisel2").label("\u5bf9 2");
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"boxsel5", "boxsel6"});
    model.component("comp1").geom("geom1").feature("unisel3").label("\u5bf9 3");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat1").label("Barium Titanate (poled)");
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
         .set("relpermittivity", new String[]{"1115.1", "0", "0", "0", "1115.1", "0", "0", "0", "1251.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "5700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("sE", new String[]{"9.1e-012[1/Pa]", "-2.7e-012[1/Pa]", "-2.9e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-2.7e-012[1/Pa]", "9.1e-012[1/Pa]", "-2.9e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-2.9e-012[1/Pa]", "-2.9e-012[1/Pa]", "9.5e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "2.28e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "2.28e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "2.36e-011[1/Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-7.8e-011[C/N]", "0[C/N]", "0[C/N]", "-7.8e-011[C/N]", "0[C/N]", "0[C/N]", "1.9e-010[C/N]", "0[C/N]", 
         "2.6e-010[C/N]", "0[C/N]", "2.6e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"1450", "0", "0", "0", "1450", "0", "0", "0", "1700"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.50377e+011[Pa]", "6.56308e+010[Pa]", "6.59391e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "6.56308e+010[Pa]", "1.50377e+011[Pa]", "6.59391e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "6.59391e+010[Pa]", "6.59391e+010[Pa]", "1.45521e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "4.38596e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "4.38596e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "4.23729e+010[Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-4.32015[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-4.32015[C/m^2]", "0[C/m^2]", "0[C/m^2]", "17.3624[C/m^2]", "0[C/m^2]", 
         "11.4035[C/m^2]", "0[C/m^2]", "11.4035[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"1115.1", "0", "0", "0", "1115.1", "0", "0", "0", "1251.3"});

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material("mat1").label("\u57fa\u4f53\uff1a\u949b\u9178\u94a1\uff08\u6781\u5316\uff09");
    model.component("comp1").material("mat1").selection().named("geom1_dif1_dom");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat2").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat2").label("Lead Zirconate Titanate (PZT-7A)");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.1);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"499.5", "0", "0", "0", "499.5", "0", "0", "0", "229.9"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.07e-011[1/Pa]", "-3.58e-012[1/Pa]", "-4.6e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-3.58e-012[1/Pa]", "1.07e-011[1/Pa]", "-4.6e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-4.6e-012[1/Pa]", "-4.6e-012[1/Pa]", "1.39e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "3.4e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "3.4e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "2.86e-011[1/Pa]"});
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-6e-011[C/N]", "0[C/N]", "0[C/N]", "-6e-011[C/N]", "0[C/N]", "0[C/N]", "1.53e-010[C/N]", "0[C/N]", 
         "3.6e-010[C/N]", "0[C/N]", "3.6e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"930", "0", "0", "0", "930", "0", "0", "0", "425"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.57701e+011[Pa]", "8.76735e+010[Pa]", "8.12032e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "8.76735e+010[Pa]", "1.57701e+011[Pa]", "8.12032e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "8.12032e+010[Pa]", "8.12032e+010[Pa]", "1.25688e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.94118e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.94118e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "3.4965e+010[Pa]"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-2.2984[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-2.2984[C/m^2]", "0[C/m^2]", "0[C/m^2]", "9.48595[C/m^2]", "0[C/m^2]", 
         "10.5882[C/m^2]", "0[C/m^2]", "10.5882[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"499.5", "0", "0", "0", "499.5", "0", "0", "0", "229.9"});
    model.component("comp1").material("mat2").label("\u7ea4\u7ef4\uff1a\u9506\u949b\u9178\u94c5 (PZT-7A)");
    model.component("comp1").material("mat2").selection().named("geom1_ext1_dom");

    model.component("comp1").physics("solid").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid").feature("cp1").set("computeElasticityMatrixVoigt", true);
    model.component("comp1").physics("solid").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection().named("geom1_unisel1");
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection("destinationDomains")
         .named("geom1_boxsel2");
    model.component("comp1").physics("solid").feature("cp1").create("bp2", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection().named("geom1_unisel2");
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection("destinationDomains")
         .named("geom1_boxsel4");
    model.component("comp1").physics("solid").feature("cp1").create("bp3", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection().named("geom1_unisel3");
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection("destinationDomains")
         .named("geom1_boxsel6");
    model.component("comp1").physics("solid").feature("cp1").set("parametricStudy", "yes");
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterName", "v_f", 0, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterRange", "range(0.1,0.1,0.7)", 0, 0);
    model.component("comp1").physics("solid").feature("cp1").runCommand("createLoadGroupsandStudy");

    model.group().create("lg1", "LoadGroup");

    model.nodeGroup("cpgrp").add("group", "lg1");

    model.group().create("lg2", "LoadGroup");

    model.nodeGroup("cpgrp").add("group", "lg2");

    model.group().create("lg3", "LoadGroup");

    model.nodeGroup("cpgrp").add("group", "lg3");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 2);
    model.component("comp1").cpl("genext1").selection().named("geom1_boxsel1");
    model.component("comp1").cpl("genext1").set("method", "closest");
    model.component("comp1").cpl().duplicate("genext2", "genext1");
    model.component("comp1").cpl("genext2").selection().named("geom1_boxsel3");
    model.component("comp1").cpl().duplicate("genext3", "genext2");
    model.component("comp1").cpl("genext3").selection().named("geom1_boxsel5");

    model.component("comp1").physics("es").create("constr1", "PointwiseConstraint", 2);
    model.component("comp1").physics("es").feature("constr1")
         .label("\u5468\u671f\u6027\u6761\u4ef6\uff0c\u8fb9\u754c\u5bf9 1");
    model.component("comp1").physics("es").feature("constr1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("es").feature("constr1")
         .set("constraintExpression", "V+group.lg1*1[V/m]*W-genext1(V)");
    model.component("comp1").physics("es").feature().duplicate("constr2", "constr1");
    model.component("comp1").physics("es").feature("constr2")
         .label("\u5468\u671f\u6027\u6761\u4ef6\uff0c\u8fb9\u754c\u5bf9 2");
    model.component("comp1").physics("es").feature("constr2").selection().named("geom1_boxsel4");
    model.component("comp1").physics("es").feature("constr2")
         .set("constraintExpression", "V+group.lg2*1[V/m]*D-genext2(V)");
    model.component("comp1").physics("es").feature().duplicate("constr3", "constr2");
    model.component("comp1").physics("es").feature("constr3")
         .label("\u5468\u671f\u6027\u6761\u4ef6\uff0c\u8fb9\u754c\u5bf9 3");
    model.component("comp1").physics("es").feature("constr3").selection().named("geom1_boxsel6");
    model.component("comp1").physics("es").feature("constr3")
         .set("constraintExpression", "V+group.lg3*1[V/m]*H-genext3(V)");
    model.component("comp1").physics("es").create("gnd1", "Ground", 0);
    model.component("comp1").physics("es").feature("gnd1").selection().set(1);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("e11", "withsol('solidcp1solp',intop1(solid.DpzeX)/intop1(1),setval(loadcase,1),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e21", "withsol('solidcp1solp',intop1(solid.DpzeY)/intop1(1),setval(loadcase,1),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c21 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e31", "withsol('solidcp1solp',intop1(solid.DpzeZ)/intop1(1),setval(loadcase,1),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c31 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e12", "withsol('solidcp1solp',intop1(solid.DpzeX)/intop1(1),setval(loadcase,2),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e22", "withsol('solidcp1solp',intop1(solid.DpzeY)/intop1(1),setval(loadcase,2),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e32", "withsol('solidcp1solp',intop1(solid.DpzeZ)/intop1(1),setval(loadcase,2),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c32 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e13", "withsol('solidcp1solp',intop1(solid.DpzeX)/intop1(1),setval(loadcase,3),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c13 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e23", "withsol('solidcp1solp',intop1(solid.DpzeY)/intop1(1),setval(loadcase,3),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c23 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e33", "withsol('solidcp1solp',intop1(solid.DpzeZ)/intop1(1),setval(loadcase,3),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c33 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e14", "withsol('solidcp1solp',intop1(solid.DpzeX)/intop1(1),setval(loadcase,5),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c14 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e24", "withsol('solidcp1solp',intop1(solid.DpzeY)/intop1(1),setval(loadcase,5),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c24 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e34", "withsol('solidcp1solp',intop1(solid.DpzeZ)/intop1(1),setval(loadcase,5),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c34 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e15", "withsol('solidcp1solp',intop1(solid.DpzeX)/intop1(1),setval(loadcase,6),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c15 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e25", "withsol('solidcp1solp',intop1(solid.DpzeY)/intop1(1),setval(loadcase,6),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c25 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e35", "withsol('solidcp1solp',intop1(solid.DpzeZ)/intop1(1),setval(loadcase,6),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c35 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e16", "withsol('solidcp1solp',intop1(solid.DpzeX)/intop1(1),setval(loadcase,4),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c16 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e26", "withsol('solidcp1solp',intop1(solid.DpzeY)/intop1(1),setval(loadcase,4),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c26 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("e36", "withsol('solidcp1solp',intop1(solid.DpzeZ)/intop1(1),setval(loadcase,4),setval(v_f,v_f))", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0c36 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("k11", "withsol('solidcp1solp',intop1(solid.DpzeX)/intop1(1[V/m]),setval(loadcase,7),setval(v_f,v_f))", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("k12", "withsol('solidcp1solp',intop1(solid.DpzeY)/intop1(1[V/m]),setval(loadcase,7),setval(v_f,v_f))", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("k13", "withsol('solidcp1solp',intop1(solid.DpzeZ)/intop1(1[V/m]),setval(loadcase,7),setval(v_f,v_f))", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\uff0c13 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("k22", "withsol('solidcp1solp',intop1(solid.DpzeY)/intop1(1[V/m]),setval(loadcase,8),setval(v_f,v_f))", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("k23", "withsol('solidcp1solp',intop1(solid.DpzeZ)/intop1(1[V/m]),setval(loadcase,8),setval(v_f,v_f))", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\uff0c23 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("k33", "withsol('solidcp1solp',intop1(solid.DpzeZ)/intop1(1[V/m]),setval(loadcase,9),setval(v_f,v_f))", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\uff0c33 \u5206\u91cf");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 3);

    return model;
  }

  public static Model run2(Model model) {
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 6, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 6, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", true, 6, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 7, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 7, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", true, 7, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 0);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 1);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 2);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 3);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 4);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 5);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 6);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 7);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", false, 8, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroupweight", "1.0", 8, 8);
    model.study("solidcp1std").feature("solidcp1stat").setIndex("loadgroup", true, 8, 8);
    model.study("solidcp1std").createAutoSequences("all");

    model.batch("solidcp1p").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").setIndex("looplevel", 7, 1);
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
    model.result().evaluationGroup().create("solidcp1stdEg", "EvaluationGroup");
    model.result().evaluationGroup("solidcp1stdEg").set("data", "dset2");
    model.result().evaluationGroup("solidcp1stdEg").set("includeparameters", "off");
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("solidcp1stdEg")
         .label("\u6709\u6548\u6750\u6599\u5c5e\u6027 (\u5355\u5143\u5468\u671f\u6027\u7814\u7a76)");
    model.result().evaluationGroup("solidcp1stdEg").create("gmevevcp1", "EvalGlobalMatrix");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevevcp1").set("expr", "root.comp1.solid.cp1.DVo");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevevcp1")
         .set("descr", "\u5f39\u6027\u77e9\u9635\uff0cVoigt \u6807\u8bb0");
    model.result().evaluationGroup("solidcp1stdEg").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 9, 0);
    model.result("pg2").setIndex("looplevel", 7, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "V");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 9, 0);
    model.result("pg3").setIndex("looplevel", 7, 1);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 11");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "first", 0);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 11 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "v<sub>f</sub>");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "c<sub>11</sub> (GPa)");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "solid.cp1.DVo11", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "GPa", 0);
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 33");
    model.result("pg5")
         .set("title", "\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 33 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg5").set("ylabel", "c<sub>33</sub> (GPa)");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "solid.cp1.DVo33", 0);
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6").label("\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 44");
    model.result("pg6")
         .set("title", "\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 44 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg6").set("ylabel", "c<sub>44</sub> (GPa)");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "solid.cp1.DVo44", 0);
    model.result("pg6").run();
    model.result("pg4").run();
    model.result().duplicate("pg7", "pg4");
    model.result("pg7").run();
    model.result("pg7").label("\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 66");
    model.result("pg7")
         .set("title", "\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 66 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg7").set("ylabel", "c<sub>66</sub> (GPa)");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("expr", "solid.cp1.DVo66", 0);
    model.result("pg7").run();
    model.result("pg4").run();
    model.result().duplicate("pg8", "pg4");
    model.result("pg8").run();
    model.result("pg8").label("\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 12");
    model.result("pg8")
         .set("title", "\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 12 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg8").set("ylabel", "c<sub>12</sub> (GPa)");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "solid.cp1.DVo12", 0);
    model.result("pg8").run();
    model.result("pg4").run();
    model.result().duplicate("pg9", "pg4");
    model.result("pg9").run();
    model.result("pg9").label("\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 23");
    model.result("pg9")
         .set("title", "\u5747\u8d28\u5f39\u6027\u5f20\u91cf\u5206\u91cf 23 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg9").set("ylabel", "c<sub>23</sub> (GPa)");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").setIndex("expr", "solid.cp1.DVo23", 0);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\u5206\u91cf 31");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").setIndex("looplevelinput", "first", 0);
    model.result("pg10").set("titletype", "manual");
    model.result("pg10")
         .set("title", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\u5206\u91cf 31 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "v<sub>f</sub>");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "e<sub>31</sub> (C/m<sup>2</sup>)");
    model.result("pg10").set("showlegends", false);
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").setIndex("expr", "e31", 0);
    model.result("pg10").feature("glob1").setIndex("unit", "C/m^2", 0);
    model.result("pg10").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\u5206\u91cf 33");
    model.result("pg11")
         .set("title", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\u5206\u91cf 33 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg11").set("ylabel", "e<sub>33</sub> (C/m<sup>2</sup>)");
    model.result("pg11").run();
    model.result("pg11").feature("glob1").setIndex("expr", "e33", 0);
    model.result("pg11").feature("glob1").setIndex("unit", "C/m^2", 0);
    model.result("pg11").run();
    model.result("pg10").run();
    model.result().duplicate("pg12", "pg10");
    model.result("pg12").run();
    model.result("pg12").label("\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\u5206\u91cf 15");
    model.result("pg12")
         .set("title", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\u5206\u91cf 15 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg12").set("ylabel", "e<sub>15</sub> (C/m<sup>2</sup>)");
    model.result("pg12").run();
    model.result("pg12").feature("glob1").setIndex("expr", "e15", 0);
    model.result("pg12").feature("glob1").setIndex("unit", "C/m^2", 0);
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\u5206\u91cf 11");
    model.result("pg13").set("data", "dset2");
    model.result("pg13").setIndex("looplevelinput", "first", 0);
    model.result("pg13").set("titletype", "manual");
    model.result("pg13")
         .set("title", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\u5206\u91cf 11 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg13").set("xlabelactive", true);
    model.result("pg13").set("xlabel", "v<sub>f</sub>");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "k<sub>11</sub> (F/m)");
    model.result("pg13").set("showlegends", false);
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").setIndex("expr", "k11", 0);
    model.result("pg13").feature("glob1").setIndex("unit", "F/m", 0);
    model.result("pg13").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg13").run();
    model.result().duplicate("pg14", "pg13");
    model.result("pg14").run();
    model.result("pg14").label("\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\u5206\u91cf 33");
    model.result("pg14")
         .set("title", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\u5206\u91cf 33 vs. \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.result("pg14").set("ylabel", "k<sub>33</sub> (F/m)");
    model.result("pg14").run();
    model.result("pg14").feature("glob1").setIndex("expr", "k33", 0);
    model.result("pg14").feature("glob1").setIndex("unit", "F/m", 0);
    model.result("pg14").run();
    model.result().evaluationGroup("solidcp1stdEg")
         .label("\u5747\u8d28\u5f39\u6027\u5f20\u91cf\uff0870% \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570\uff09");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1")
         .label("\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf\uff0870% \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570\uff09");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").set("concatenation", "vertical");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "e11", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c11 \u5206\u91cf", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "e12", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c12 \u5206\u91cf", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "e13", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c13 \u5206\u91cf", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "e14", 3);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c14 \u5206\u91cf", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "e15", 4);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c15 \u5206\u91cf", 4);
    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "e21", 0);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c21 \u5206\u91cf", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "e22", 1);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c22 \u5206\u91cf", 1);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "e23", 2);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c23 \u5206\u91cf", 2);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "e24", 3);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c24 \u5206\u91cf", 3);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "e25", 4);
    model.result().evaluationGroup("eg1").feature("gev2")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c25 \u5206\u91cf", 4);
    model.result().evaluationGroup("eg1").feature().duplicate("gev3", "gev2");
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "e31", 0);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c31 \u5206\u91cf", 0);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "e32", 1);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c32 \u5206\u91cf", 1);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "e33", 2);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c33 \u5206\u91cf", 2);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "e34", 3);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c34 \u5206\u91cf", 3);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "e35", 4);
    model.result().evaluationGroup("eg1").feature("gev3")
         .setIndex("descr", "\u5747\u8d28\u538b\u7535\u8026\u5408\u5f20\u91cf (C/m^2)\uff0c35 \u5206\u91cf", 4);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");

    return model;
  }

  public static Model run3(Model model) {
    model.result().evaluationGroup("eg2")
         .label("\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\uff0870% \u7ea4\u7ef4\u4f53\u79ef\u5206\u6570\uff09");
    model.result().evaluationGroup("eg2").set("data", "dset2");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg2").set("includeparameters", false);
    model.result().evaluationGroup("eg2").set("concatenation", "vertical");
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "k11", 0);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf (F/m)\uff0c11 \u5206\u91cf", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "k12", 1);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf (F/m)\uff0c12 \u5206\u91cf", 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "k13", 2);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf (F/m)\uff0c13 \u5206\u91cf", 2);
    model.result().evaluationGroup("eg2").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("expr", "k12", 0);
    model.result().evaluationGroup("eg2").feature("gev2")
         .setIndex("descr", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf (F/m)\uff0c12 \u5206\u91cf", 0);
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("expr", "k22", 1);
    model.result().evaluationGroup("eg2").feature("gev2")
         .setIndex("descr", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf (F/m)\uff0c22 \u5206\u91cf", 1);
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("expr", "k23", 2);
    model.result().evaluationGroup("eg2").feature("gev2")
         .setIndex("descr", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf (F/m)\uff0c23 \u5206\u91cf", 2);
    model.result().evaluationGroup("eg2").feature().duplicate("gev3", "gev2");
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("expr", "k13", 0);
    model.result().evaluationGroup("eg2").feature("gev3")
         .setIndex("descr", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf (F/m)\uff0c13 \u5206\u91cf", 0);
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("expr", "k23", 1);
    model.result().evaluationGroup("eg2").feature("gev3")
         .setIndex("descr", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf (F/m)\uff0c23 \u5206\u91cf", 1);
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("expr", "k33", 2);
    model.result().evaluationGroup("eg2").feature("gev3")
         .setIndex("descr", "\u5747\u8d28\u4ecb\u7535\u5e38\u6570\u5f20\u91cf (F/m)\uff0c33 \u5206\u91cf", 2);
    model.result().evaluationGroup("eg2").run();
    model.result("pg4").run();

    model.title("\u538b\u7535\u7ea4\u7ef4\u590d\u5408\u6750\u6599\u7684\u7ec6\u89c2\u529b\u5b66\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u538b\u7535\u7ea4\u7ef4\u590d\u5408\u6750\u6599\u7684\u7ec6\u89c2\u529b\u5b66\u5c5e\u6027\uff0c\u8be5\u590d\u5408\u6750\u6599\u7684\u5747\u8d28\u673a\u7535\u5c5e\u6027\u6e90\u81ea\u57fa\u4f53\u548c\u7ea4\u7ef4\u5404\u81ea\u7684\u5fae\u89c2\u5c5e\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("solidcp1sol").clearSolutionData();
    model.sol("solidcp1solp").clearSolutionData();
    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("micromechanical_model_of_a_piezoelectric_composite.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
