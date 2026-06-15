/*
 * failure_prediction_in_a_layered_shell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:23 by COMSOL 6.3.0.290. */
public class failure_prediction_in_a_layered_shell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");
    model.component("comp1").physics().create("shell2", "Shell", "geom1");
    model.component("comp1").physics().create("shell3", "Shell", "geom1");
    model.component("comp1").physics().create("shell4", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/shell2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/shell3", true);
    model.study("std1").feature("stat").setSolveFor("/physics/shell4", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("th", "0.05e-3[m]", "\u94fa\u5c42\u539a\u5ea6");
    model.param().set("Ftotal", "15[N]", "\u603b\u8fb9\u8f7d\u8377");
    model.param().set("E1", "207[GPa]", "\u6768\u6c0f\u6a21\u91cf 11");
    model.param().set("E2", "7.6[GPa]", "\u6768\u6c0f\u6a21\u91cf 22");
    model.param().set("E3", "E2", "\u6768\u6c0f\u6a21\u91cf 33");
    model.param().set("nu12", "0.3", "\u6cca\u677e\u6bd4 12");
    model.param().set("nu23", "0", "\u6cca\u677e\u6bd4 23");
    model.param().set("G", "5[GPa]", "\u526a\u5207\u6a21\u91cf");
    model.param().set("Sigmats1", "500[MPa]", "\u6297\u62c9\u5f3a\u5ea6 11");
    model.param().set("Sigmats2", "5[MPa]", "\u6297\u62c9\u5f3a\u5ea6 22");
    model.param().set("Sigmats3", "Sigmats2", "\u6297\u62c9\u5f3a\u5ea6 33");
    model.param().set("Sigmacs1", "350[MPa]", "\u6297\u538b\u5f3a\u5ea6 11");
    model.param().set("Sigmacs2", "75[MPa]", "\u6297\u538b\u5f3a\u5ea6 22");
    model.param().set("Sigmacs3", "Sigmacs2", "\u6297\u538b\u5f3a\u5ea6 33");
    model.param().set("Sigmass23", "35[MPa]", "\u526a\u5207\u5f3a\u5ea6 23");
    model.param().set("Sigmass13", "Sigmass23", "\u526a\u5207\u5f3a\u5ea6 13");
    model.param().set("Sigmass12", "Sigmass23", "\u526a\u5207\u5f3a\u5ea6 12");

    model.component("comp1").coordSystem().create("sys2", "Rotated");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"pi/2", "0", "0"});
    model.component("comp1").coordSystem().duplicate("sys3", "sys2");
    model.component("comp1").coordSystem("sys3").set("angle", new String[]{"-pi/4", "0", "0"});
    model.component("comp1").coordSystem().duplicate("sys4", "sys3");
    model.component("comp1").coordSystem("sys4").set("angle", new String[]{"pi/4", "0", "0"});

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "1e-2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");

    model.component("comp1").physics("shell").label("\u94fa\u5c42 1");
    model.component("comp1").physics("shell").tag("shell1");
    model.component("comp1").physics("shell1").prop("z").set("z", 0);
    model.component("comp1").physics("shell1").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("shell1").feature("to1").set("d", "th");
    model.component("comp1").physics("shell1").feature("to1").set("OffsetDefinition", "RelativeDistance");
    model.component("comp1").physics("shell1").feature("to1").set("z_offset_rel", -3);
    model.component("comp1").physics("shell1").feature("emm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell1").feature("emm1").set("TransverseIsotropic", true);
    model.component("comp1").physics("shell1").feature("emm1").feature("shls1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("shell2").label("\u94fa\u5c42 2");
    model.component("comp1").physics("shell2").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("shell2").prop("z").set("z", 0);
    model.component("comp1").physics("shell2").field("displacement").field("u");
    model.component("comp1").physics("shell2").field("dimensionless_displacement").field("ar");
    model.component("comp1").physics("shell2").feature("to1").set("d", "th");
    model.component("comp1").physics("shell2").feature("to1").set("OffsetDefinition", "RelativeDistance");
    model.component("comp1").physics("shell2").feature("to1").set("z_offset_rel", -1);
    model.component("comp1").physics("shell2").feature("emm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell2").feature("emm1").set("TransverseIsotropic", true);
    model.component("comp1").physics("shell2").feature("emm1").feature("shls1").set("coordinateSystem", "sys3");
    model.component("comp1").physics("shell3").label("\u94fa\u5c42 3");
    model.component("comp1").physics("shell3").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("shell3").prop("z").set("z", 0);
    model.component("comp1").physics("shell3").field("displacement").field("u");
    model.component("comp1").physics("shell3").field("dimensionless_displacement").field("ar");
    model.component("comp1").physics("shell3").feature("to1").set("d", "th");
    model.component("comp1").physics("shell3").feature("to1").set("OffsetDefinition", "RelativeDistance");
    model.component("comp1").physics("shell3").feature("to1").set("z_offset_rel", 1);
    model.component("comp1").physics("shell3").feature("emm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell3").feature("emm1").set("TransverseIsotropic", true);
    model.component("comp1").physics("shell3").feature("emm1").feature("shls1").set("coordinateSystem", "sys4");
    model.component("comp1").physics("shell4").label("\u94fa\u5c42 4");
    model.component("comp1").physics("shell4").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("shell4").prop("z").set("z", 0);
    model.component("comp1").physics("shell4").field("displacement").field("u");
    model.component("comp1").physics("shell4").field("dimensionless_displacement").field("ar");
    model.component("comp1").physics("shell4").feature("to1").set("d", "th");
    model.component("comp1").physics("shell4").feature("to1").set("OffsetDefinition", "RelativeDistance");
    model.component("comp1").physics("shell4").feature("to1").set("z_offset_rel", 3);
    model.component("comp1").physics("shell4").feature("emm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell4").feature("emm1").set("TransverseIsotropic", true);

    model.component("comp1").material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely_isotropic");
    model.component("comp1").material("mat1").propertyGroup("TransverseIsotropic")
         .set("Evect", new String[]{"E1", "E2"});
    model.component("comp1").material("mat1").propertyGroup("TransverseIsotropic")
         .set("nuvect", new String[]{"nu12", "nu23"});
    model.component("comp1").material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", new String[]{"G"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1500"});

    model.component("comp1").physics("shell1").feature("emm1").create("sf1", "Safety", 2);
    model.component("comp1").physics("shell1").feature("emm1").feature("sf1")
         .label("\u5b89\u5168\u6027\uff1aTsai\u2013Wu \u6b63\u4ea4\u5404\u5411\u5f02\u6027\uff0c\u5e73\u9762\u5e94\u529b\u51c6\u5219");
    model.component("comp1").physics("shell1").feature("emm1").feature("sf1")
         .set("FailureCriterion", "Tsai-Wu Orthotropic");
    model.component("comp1").physics("shell1").feature("emm1").feature("sf1").set("usePlaneStressVersion", true);
    model.component("comp1").physics("shell1").feature("emm1").feature().duplicate("sf2", "sf1");
    model.component("comp1").physics("shell1").feature("emm1").feature("sf2").set("FailureCriterion", "Hoffman");
    model.component("comp1").physics("shell1").feature("emm1").feature().duplicate("sf3", "sf2");
    model.component("comp1").physics("shell1").feature("emm1").feature("sf3").set("FailureCriterion", "Tsai-Hill");
    model.component("comp1").physics("shell1").feature("emm1").feature().duplicate("sf4", "sf3");
    model.component("comp1").physics("shell1").feature("emm1").feature("sf4")
         .set("FailureCriterion", "Azzi-Tsai-Hill");
    model.component("comp1").physics("shell1").feature("emm1").feature().duplicate("sf5", "sf4");
    model.component("comp1").physics("shell1").feature("emm1").feature("sf5").set("FailureCriterion", "Norris");
    model.component("comp1").physics("shell1").feature("emm1").feature().duplicate("sf6", "sf5");
    model.component("comp1").physics("shell1").feature("emm1").feature("sf6")
         .set("FailureCriterion", "Tsai-Wu Anisotropic");

    model.component("comp1").material("mat1").propertyGroup()
         .create("OrthotropicStrengthParameters", "OrthotropicStrengthParameters", "Orthotropic_strength_parameters_in_Voigt_notation");
    model.component("comp1").material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmats", new String[]{"Sigmats1", "Sigmats2", "Sigmats3"});
    model.component("comp1").material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmacs", new String[]{"Sigmacs1", "Sigmacs2", "Sigmacs3"});
    model.component("comp1").material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmass", new String[]{"Sigmass23", "Sigmass13", "Sigmass12"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("AnisotropicStrengthParameters", "AnisotropicStrengthParameters", "Anisotropic_strength_parameters_in_Voigt_notation");
    model.component("comp1").material("mat1").propertyGroup("AnisotropicStrengthParameters")
         .set("F_s", new String[]{"1/Sigmats1-1/Sigmacs1", "1/Sigmats2-1/Sigmacs2", "1/Sigmats3-1/Sigmacs3", "0", "0", "0"});
    model.component("comp1").material("mat1").propertyGroup("AnisotropicStrengthParameters")
         .set("F_f", new String[]{"1/(Sigmats1*Sigmacs1)", "-0.5*sqrt(1/((Sigmats1*Sigmacs1)*(Sigmats2*Sigmacs2)))", "1/(Sigmats2*Sigmacs2)", "-0.5*sqrt(1/((Sigmats1*Sigmacs1)*(Sigmats3*Sigmacs3)))", "-0.5*sqrt(1/((Sigmats2*Sigmacs2)*(Sigmats3*Sigmacs3)))", "1/(Sigmats3*Sigmacs3)", "0", "0", "0", "1/Sigmass23^2", 
         "0", "0", "0", "0", "1/Sigmass13^2", "0", "0", "0", "0", "0", 
         "1/Sigmass12^2"});

    model.component("comp1").physics("shell2").feature("emm1").feature().copy("sf1", "shell1/emm1/sf1");
    model.component("comp1").physics("shell2").feature("emm1").feature().copy("sf2", "shell1/emm1/sf2");
    model.component("comp1").physics("shell2").feature("emm1").feature().copy("sf3", "shell1/emm1/sf3");
    model.component("comp1").physics("shell2").feature("emm1").feature().copy("sf4", "shell1/emm1/sf4");
    model.component("comp1").physics("shell2").feature("emm1").feature().copy("sf5", "shell1/emm1/sf5");
    model.component("comp1").physics("shell2").feature("emm1").feature().copy("sf6", "shell1/emm1/sf6");
    model.component("comp1").physics("shell3").feature("emm1").feature().copy("sf1", "shell1/emm1/sf1");
    model.component("comp1").physics("shell3").feature("emm1").feature().copy("sf2", "shell1/emm1/sf2");
    model.component("comp1").physics("shell3").feature("emm1").feature().copy("sf3", "shell1/emm1/sf3");
    model.component("comp1").physics("shell3").feature("emm1").feature().copy("sf4", "shell1/emm1/sf4");
    model.component("comp1").physics("shell3").feature("emm1").feature().copy("sf5", "shell1/emm1/sf5");
    model.component("comp1").physics("shell3").feature("emm1").feature().copy("sf6", "shell1/emm1/sf6");
    model.component("comp1").physics("shell4").feature("emm1").feature().copy("sf1", "shell1/emm1/sf1");
    model.component("comp1").physics("shell4").feature("emm1").feature().copy("sf2", "shell1/emm1/sf2");
    model.component("comp1").physics("shell4").feature("emm1").feature().copy("sf3", "shell1/emm1/sf3");
    model.component("comp1").physics("shell4").feature("emm1").feature().copy("sf4", "shell1/emm1/sf4");
    model.component("comp1").physics("shell4").feature("emm1").feature().copy("sf5", "shell1/emm1/sf5");
    model.component("comp1").physics("shell4").feature("emm1").feature().copy("sf6", "shell1/emm1/sf6");
    model.component("comp1").physics("shell1").create("fix1", "Fixed", 0);
    model.component("comp1").physics("shell1").feature("fix1").selection().set(1);
    model.component("comp1").physics("shell1").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("shell1").feature("el1").selection().set(4);
    model.component("comp1").physics("shell1").feature("el1").set("forceType", "TotalForce");
    model.component("comp1").physics("shell1").feature("el1").set("force", new String[]{"Ftotal/4", "0", "0"});
    model.component("comp1").physics("shell2").feature().copy("fix1", "shell1/fix1");
    model.component("comp1").physics("shell2").feature().copy("el1", "shell1/el1");
    model.component("comp1").physics("shell3").feature().copy("fix1", "shell1/fix1");
    model.component("comp1").physics("shell3").feature().copy("el1", "shell1/el1");
    model.component("comp1").physics("shell4").feature().copy("fix1", "shell1/fix1");
    model.component("comp1").physics("shell4").feature().copy("el1", "shell1/el1");
    model.component("comp1").physics("shell2").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("shell2").feature("disp1").selection().set(2);
    model.component("comp1").physics("shell2").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell2").feature("disp1").setIndex("U0", "-u0", 0);
    model.component("comp1").physics("shell2").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("shell2").feature("ge1").setIndex("name", "u0", 0, 0);
    model.component("comp1").physics("shell2").feature("ge1").set("DependentVariableQuantity", "displacement");
    model.component("comp1").physics("shell3").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("shell3").feature("disp1").selection().set(2);
    model.component("comp1").physics("shell3").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell3").feature("disp1").setIndex("U0", "u0", 0);

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", "0.5e-2");
    model.result().dataset("cpt1").set("pointy", "0.5e-2");
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u94fa\u5c42 1 \u5931\u6548\u6307\u6570");
    model.result().evaluationGroup("eg1").set("data", "cpt1");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "shell1.emm1.sf1.f_im", 0);
    model.result().evaluationGroup("eg1").feature("pev1")
         .setIndex("descr", "Tsai-Wu \u6b63\u4ea4\u5404\u5411\u5f02\u6027\u5931\u6548\u6307\u6570\uff0c\u4e2d\u5c42\uff0c\u5e73\u9762\u5e94\u529b", 0);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "shell1.emm1.sf2.f_im", 1);
    model.result().evaluationGroup("eg1").feature("pev1")
         .setIndex("descr", "Hoffman \u5931\u6548\u6307\u6570\uff0c\u4e2d\u5c42", 1);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "shell1.emm1.sf3.f_im", 2);
    model.result().evaluationGroup("eg1").feature("pev1")
         .setIndex("descr", "Tsai-Hill \u5931\u6548\u6307\u6570\uff0c\u4e2d\u5c42\uff0c\u5e73\u9762\u5e94\u529b", 2);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "shell1.emm1.sf4.f_im", 3);
    model.result().evaluationGroup("eg1").feature("pev1")
         .setIndex("descr", "Azzi-Tsai-Hill \u5931\u6548\u6307\u6570\uff0c\u4e2d\u5c42", 3);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "shell1.emm1.sf5.f_im", 4);
    model.result().evaluationGroup("eg1").feature("pev1")
         .setIndex("descr", "Norris \u5931\u6548\u6307\u6570\uff0c\u4e2d\u5c42", 4);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "shell1.emm1.sf6.f_im", 5);
    model.result().evaluationGroup("eg1").feature("pev1")
         .setIndex("descr", "Tsai-Wu \u5404\u5411\u5f02\u6027\u5931\u6548\u6307\u6570\uff0c\u4e2d\u5c42", 5);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").feature("pev1").setIndex("expr", "shell2.emm1.sf1.f_im", 0);
    model.result().evaluationGroup("eg2").feature("pev1")
         .setIndex("descr", "Tsai-Wu orthotropic failure index, middle, plane stress", 0);
    model.result().evaluationGroup("eg2").feature("pev1").setIndex("expr", "shell2.emm1.sf2.f_im", 1);
    model.result().evaluationGroup("eg2").feature("pev1").setIndex("descr", "Hoffman failure index, middle", 1);
    model.result().evaluationGroup("eg2").feature("pev1").setIndex("expr", "shell2.emm1.sf3.f_im", 2);
    model.result().evaluationGroup("eg2").feature("pev1")
         .setIndex("descr", "Tsai-Hill failure index, middle, plane stress", 2);
    model.result().evaluationGroup("eg2").feature("pev1").setIndex("expr", "shell2.emm1.sf4.f_im", 3);
    model.result().evaluationGroup("eg2").feature("pev1")
         .setIndex("descr", "Azzi-Tsai-Hill failure index, middle", 3);
    model.result().evaluationGroup("eg2").feature("pev1").setIndex("expr", "shell2.emm1.sf5.f_im", 4);
    model.result().evaluationGroup("eg2").feature("pev1").setIndex("descr", "Norris failure index, middle", 4);
    model.result().evaluationGroup("eg2").feature("pev1").setIndex("expr", "shell2.emm1.sf6.f_im", 5);
    model.result().evaluationGroup("eg2").feature("pev1")
         .setIndex("descr", "Tsai-Wu anisotropic failure index, middle", 5);
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().duplicate("eg3", "eg2");
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("expr", "shell3.emm1.sf1.f_im", 0);
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("expr", "shell3.emm1.sf2.f_im", 1);
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("expr", "shell3.emm1.sf3.f_im", 2);
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("expr", "shell3.emm1.sf4.f_im", 3);
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("expr", "shell3.emm1.sf5.f_im", 4);
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("expr", "shell3.emm1.sf6.f_im", 5);
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup().duplicate("eg4", "eg3");
    model.result().evaluationGroup("eg4").feature("pev1").setIndex("expr", "shell4.emm1.sf1.f_im", 0);
    model.result().evaluationGroup("eg4").feature("pev1").setIndex("expr", "shell4.emm1.sf2.f_im", 1);
    model.result().evaluationGroup("eg4").feature("pev1").setIndex("expr", "shell4.emm1.sf3.f_im", 2);
    model.result().evaluationGroup("eg4").feature("pev1").setIndex("expr", "shell4.emm1.sf4.f_im", 3);
    model.result().evaluationGroup("eg4").feature("pev1").setIndex("expr", "shell4.emm1.sf5.f_im", 4);
    model.result().evaluationGroup("eg4").feature("pev1").setIndex("expr", "shell4.emm1.sf6.f_im", 5);
    model.result().evaluationGroup("eg4").run();
    model.result().evaluationGroup().create("eg5", "EvaluationGroup");
    model.result().evaluationGroup("eg5").label("\u94fa\u5c42 1 \u5b89\u5168\u7cfb\u6570");
    model.result().evaluationGroup("eg5").set("data", "cpt1");
    model.result().evaluationGroup("eg5").set("transpose", true);
    model.result().evaluationGroup("eg5").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg5").feature("pev1").setIndex("expr", "shell1.emm1.sf1.s_fm", 0);
    model.result().evaluationGroup("eg5").feature("pev1")
         .setIndex("descr", "Tsai-Wu \u6b63\u4ea4\u5404\u5411\u5f02\u6027\u5b89\u5168\u7cfb\u6570\uff0c\u4e2d\u5c42\uff0c\u5e73\u9762\u5e94\u529b", 0);
    model.result().evaluationGroup("eg5").feature("pev1").setIndex("expr", "shell1.emm1.sf2.s_fm", 1);
    model.result().evaluationGroup("eg5").feature("pev1")
         .setIndex("descr", "Hoffman \u5b89\u5168\u7cfb\u6570\uff0c\u4e2d\u5c42", 1);
    model.result().evaluationGroup("eg5").feature("pev1").setIndex("expr", "shell1.emm1.sf3.s_fm", 2);
    model.result().evaluationGroup("eg5").feature("pev1")
         .setIndex("descr", "Tsai-Hill \u5b89\u5168\u7cfb\u6570\uff0c\u4e2d\u5c42\uff0c\u5e73\u9762\u5e94\u529b", 2);
    model.result().evaluationGroup("eg5").feature("pev1").setIndex("expr", "shell1.emm1.sf4.s_fm", 3);
    model.result().evaluationGroup("eg5").feature("pev1")
         .setIndex("descr", "Azzi-Tsai-Hill \u5b89\u5168\u7cfb\u6570\uff0c\u4e2d\u5c42", 3);
    model.result().evaluationGroup("eg5").feature("pev1").setIndex("expr", "shell1.emm1.sf5.s_fm", 4);
    model.result().evaluationGroup("eg5").feature("pev1")
         .setIndex("descr", "Norris \u5b89\u5168\u7cfb\u6570\uff0c\u4e2d\u5c42", 4);
    model.result().evaluationGroup("eg5").feature("pev1").setIndex("expr", "shell1.emm1.sf6.s_fm", 5);
    model.result().evaluationGroup("eg5").feature("pev1")
         .setIndex("descr", "Tsai-Wu \u5404\u5411\u5f02\u6027\u5931\u6548\u6307\u6570\uff0c\u4e2d\u5c42", 5);
    model.result().evaluationGroup("eg5").run();
    model.result().evaluationGroup().duplicate("eg6", "eg5");
    model.result().evaluationGroup("eg6").feature("pev1").setIndex("expr", "shell2.emm1.sf1.s_fm", 0);
    model.result().evaluationGroup("eg6").feature("pev1")
         .setIndex("descr", "Tsai-Wu orthotropic safety factor, middle, plane stress", 0);
    model.result().evaluationGroup("eg6").feature("pev1").setIndex("expr", "shell2.emm1.sf2.s_fm", 1);
    model.result().evaluationGroup("eg6").feature("pev1").setIndex("descr", "Hoffman safety factor, middle", 1);
    model.result().evaluationGroup("eg6").feature("pev1").setIndex("expr", "shell2.emm1.sf3.s_fm", 2);
    model.result().evaluationGroup("eg6").feature("pev1")
         .setIndex("descr", "Tsai-Hill safety factor, middle, plane stress", 2);
    model.result().evaluationGroup("eg6").feature("pev1").setIndex("expr", "shell2.emm1.sf4.s_fm", 3);
    model.result().evaluationGroup("eg6").feature("pev1")
         .setIndex("descr", "Azzi-Tsai-Hill safety factor, middle", 3);
    model.result().evaluationGroup("eg6").feature("pev1").setIndex("expr", "shell2.emm1.sf5.s_fm", 4);
    model.result().evaluationGroup("eg6").feature("pev1").setIndex("descr", "Norris safety factor, middle", 4);
    model.result().evaluationGroup("eg6").feature("pev1").setIndex("expr", "shell2.emm1.sf6.s_fm", 5);
    model.result().evaluationGroup("eg6").feature("pev1")
         .setIndex("descr", "Tsai-Wu anisotropic failure index, middle", 5);
    model.result().evaluationGroup("eg6").run();
    model.result().evaluationGroup().duplicate("eg7", "eg6");
    model.result().evaluationGroup("eg7").feature("pev1").setIndex("expr", "shell3.emm1.sf1.s_fm", 0);
    model.result().evaluationGroup("eg7").feature("pev1").setIndex("expr", "shell3.emm1.sf2.s_fm", 1);
    model.result().evaluationGroup("eg7").feature("pev1").setIndex("expr", "shell3.emm1.sf3.s_fm", 2);
    model.result().evaluationGroup("eg7").feature("pev1").setIndex("expr", "shell3.emm1.sf4.s_fm", 3);
    model.result().evaluationGroup("eg7").feature("pev1").setIndex("expr", "shell3.emm1.sf5.s_fm", 4);
    model.result().evaluationGroup("eg7").feature("pev1").setIndex("expr", "shell3.emm1.sf6.s_fm", 5);
    model.result().evaluationGroup("eg7").run();
    model.result().evaluationGroup().duplicate("eg8", "eg7");
    model.result().evaluationGroup("eg8").feature("pev1").setIndex("expr", "shell4.emm1.sf1.s_fm", 0);
    model.result().evaluationGroup("eg8").feature("pev1").setIndex("expr", "shell4.emm1.sf2.s_fm", 1);
    model.result().evaluationGroup("eg8").feature("pev1").setIndex("expr", "shell4.emm1.sf3.s_fm", 2);
    model.result().evaluationGroup("eg8").feature("pev1").setIndex("expr", "shell4.emm1.sf4.s_fm", 3);
    model.result().evaluationGroup("eg8").feature("pev1").setIndex("expr", "shell4.emm1.sf5.s_fm", 4);
    model.result().evaluationGroup("eg8").feature("pev1").setIndex("expr", "shell4.emm1.sf6.s_fm", 5);
    model.result().evaluationGroup("eg8").run();
    model.result().evaluationGroup().create("eg9", "EvaluationGroup");
    model.result().evaluationGroup("eg9").label("\u94fa\u5c42 1 \u5e94\u529b");
    model.result().evaluationGroup("eg9").set("data", "cpt1");
    model.result().evaluationGroup("eg9").set("transpose", true);
    model.result().evaluationGroup("eg9").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg9").feature("pev1").setIndex("expr", "shell1.Sl11", 0);
    model.result().evaluationGroup("eg9").feature("pev1").setIndex("expr", "shell1.Sl22", 1);
    model.result().evaluationGroup("eg9").feature("pev1").setIndex("expr", "shell1.Sl12", 2);
    model.result().evaluationGroup("eg9").run();
    model.result().evaluationGroup().duplicate("eg10", "eg9");
    model.result().evaluationGroup("eg10").feature("pev1").setIndex("expr", "shell2.Sl11", 0);
    model.result().evaluationGroup("eg10").feature("pev1")
         .setIndex("descr", "Second Piola-Kirchhoff stress, local coordinate system, 11 component", 0);
    model.result().evaluationGroup("eg10").feature("pev1").setIndex("expr", "shell2.Sl22", 1);
    model.result().evaluationGroup("eg10").feature("pev1")
         .setIndex("descr", "Second Piola-Kirchhoff stress, local coordinate system, 22 component", 1);
    model.result().evaluationGroup("eg10").feature("pev1").setIndex("expr", "shell2.Sl12", 2);
    model.result().evaluationGroup("eg10").feature("pev1")
         .setIndex("descr", "Second Piola-Kirchhoff stress, local coordinate system, 12 component", 2);
    model.result().evaluationGroup("eg10").run();
    model.result().evaluationGroup().duplicate("eg11", "eg10");
    model.result().evaluationGroup("eg11").feature("pev1").setIndex("expr", "shell3.Sl11", 0);
    model.result().evaluationGroup("eg11").feature("pev1").setIndex("expr", "shell3.Sl22", 1);
    model.result().evaluationGroup("eg11").feature("pev1").setIndex("expr", "shell3.Sl12", 2);
    model.result().evaluationGroup("eg11").run();
    model.result().evaluationGroup().duplicate("eg12", "eg11");
    model.result().evaluationGroup("eg12").feature("pev1").setIndex("expr", "shell4.Sl11", 0);
    model.result().evaluationGroup("eg12").feature("pev1").setIndex("expr", "shell4.Sl22", 1);
    model.result().evaluationGroup("eg12").feature("pev1").setIndex("expr", "shell4.Sl12", 2);
    model.result().evaluationGroup("eg12").run();
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("von Mises \u5e94\u529b");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "von Mises \u5e94\u529b (MPa)");
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("arrayaxis", "z");
    model.result("pg1").set("displacementlinear", "absolute");
    model.result("pg1").set("celldisp", "30*th");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").feature("surf1").set("expr", "round(shell1.mises)");
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("expr", "round(shell2.mises)");
    model.result("pg1").feature("surf2").set("colortable", "Cyclic");
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").feature("surf3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("expr", "round(shell3.mises)");
    model.result("pg1").feature("surf3").set("colortable", "Disco");
    model.result("pg1").feature().duplicate("surf4", "surf3");
    model.result("pg1").feature("surf4").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").set("expr", "round(shell4.mises)");
    model.result("pg1").feature("surf4").set("colortable", "ThermalDark");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "rightdouble");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("Hoffman \u5b89\u5168\u7cfb\u6570");
    model.result("pg2").set("title", "Hoffman \u5b89\u5168\u7cfb\u6570 (1)");
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "shell1.emm1.sf2.s_fm");
    model.result("pg2").feature("surf2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("expr", "shell2.emm1.sf2.s_fm");
    model.result("pg2").feature("surf3").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").set("expr", "shell3.emm1.sf2.s_fm");
    model.result("pg2").feature("surf4").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").set("expr", "shell4.emm1.sf2.s_fm");
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u591a\u5c42\u58f3\u4e2d\u7684\u6545\u969c\u9884\u6d4b");

    model
         .description("\u672c\u4f8b\u4ecb\u7ecd\u5982\u4f55\u4f7f\u7528\u201c\u58f3\u201d\u63a5\u53e3\u5bf9\u5806\u53e0\u7684\u58f3\u5efa\u6a21\u3002\u6bd4\u8f83\u4e86\u5355\u8f74\u5f20\u529b\u4e0b\u4e0d\u540c\u6807\u51c6\u7684\u5931\u6548\u6307\u6807\u548c\u5b89\u5168\u7cfb\u6570\u4e0e NAFEMS \u57fa\u51c6\u7ed3\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("failure_prediction_in_a_layered_shell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
