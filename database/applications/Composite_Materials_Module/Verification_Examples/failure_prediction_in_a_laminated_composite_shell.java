/*
 * failure_prediction_in_a_laminated_composite_shell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:35 by COMSOL 6.3.0.290. */
public class failure_prediction_in_a_laminated_composite_shell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("th", "0.05e-3[m]", "\u94fa\u5c42\u539a\u5ea6");
    model.param().set("Ftotal", "15[N]", "\u603b\u8fb9\u8f7d\u8377");
    model.param().set("E1", "207[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff0c11 \u65b9\u5411");
    model.param().set("E2", "7.6[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff0c22 \u65b9\u5411");
    model.param().set("nu12", "0.3", "\u6cca\u677e\u6bd4\uff0c12 \u65b9\u5411");
    model.param().set("nu23", "0", "\u6cca\u677e\u6bd4\uff0c23 \u65b9\u5411");
    model.param().set("G", "5[GPa]", "\u526a\u5207\u6a21\u91cf");
    model.param().set("Sigmats1", "500[MPa]", "\u6297\u62c9\u5f3a\u5ea6\uff0c11 \u65b9\u5411");
    model.param().set("Sigmats2", "5[MPa]", "\u6297\u62c9\u5f3a\u5ea6\uff0c22 \u65b9\u5411");
    model.param().set("Sigmats3", "Sigmats2", "\u6297\u62c9\u5f3a\u5ea6\uff0c33 \u65b9\u5411");
    model.param().set("Sigmacs1", "350[MPa]", "\u6297\u538b\u5f3a\u5ea6\uff0c11 \u65b9\u5411");
    model.param().set("Sigmacs2", "75[MPa]", "\u6297\u538b\u5f3a\u5ea6\uff0c22 \u65b9\u5411");
    model.param().set("Sigmacs3", "Sigmacs2", "\u6297\u538b\u5f3a\u5ea6\uff0c33 \u65b9\u5411");
    model.param().set("Sigmass23", "35[MPa]", "\u526a\u5207\u5f3a\u5ea6\uff0c23 \u65b9\u5411");
    model.param().set("Sigmass13", "Sigmass23", "\u526a\u5207\u5f3a\u5ea6\uff0c13 \u65b9\u5411");
    model.param().set("Sigmass12", "Sigmass23", "\u526a\u5207\u5f3a\u5ea6\uff0c12 \u65b9\u5411");

    model.material().create("mat1", "Common", "");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("rotation", 90, 0);
    model.material("lmat1").setIndex("thickness", "th", 0);
    model.material("lmat1").setIndex("meshPoints", 1, 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", 90, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", 90, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", 90, 2);
    model.material("lmat1").setIndex("thickness", "th", 2);
    model.material("lmat1").setIndex("meshPoints", 1, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", 90, 2);
    model.material("lmat1").setIndex("thickness", "th", 2);
    model.material("lmat1").setIndex("meshPoints", 1, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 4", 3);
    model.material("lmat1").setIndex("link", "mat1", 3);
    model.material("lmat1").setIndex("rotation", 90, 3);
    model.material("lmat1").setIndex("thickness", "th", 3);
    model.material("lmat1").setIndex("meshPoints", 1, 3);
    model.material("lmat1").setIndex("tag", "lmat1_4", 3);
    model.material("lmat1").setIndex("layername", "\u5c42 4", 3);
    model.material("lmat1").setIndex("link", "mat1", 3);
    model.material("lmat1").setIndex("rotation", 90, 3);
    model.material("lmat1").setIndex("thickness", "th", 3);
    model.material("lmat1").setIndex("meshPoints", 1, 3);
    model.material("lmat1").setIndex("tag", "lmat1_4", 3);
    model.material("lmat1").setIndex("rotation", -45, 1);
    model.material("lmat1").setIndex("rotation", 45, 2);
    model.material("lmat1").setIndex("rotation", 0, 3);
    model.material("lmat1").label("\u591a\u5c42\u6750\u6599\uff1a[90/-45/45/0]");
    model.material("lmat1").set("widthRatio", 0.4);

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");

    model.component("comp1").physics("shell").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("shell").create("llem1", "LayeredElastic", 2);
    model.component("comp1").physics("shell").feature("llem1").selection().set(1);
    model.component("comp1").physics("shell").feature("llem1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell").feature("llem1").set("TransverseIsotropic", true);

    model.material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely_isotropic");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"E1", "E2"});
    model.material("mat1").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"nu12", "nu23"});
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", new String[]{"G"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"1500"});

    model.component("comp1").physics("shell").feature("llem1").create("lsf1", "LayeredSafety", 2);
    model.component("comp1").physics("shell").feature("llem1").feature("lsf1")
         .label("\u5b89\u5168\u6027\uff1aTsai\u2013Wu \u6b63\u4ea4\u5404\u5411\u5f02\u6027\uff0c\u5e73\u9762\u5e94\u529b\u51c6\u5219");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf1")
         .set("FailureCriterion", "Tsai-Wu Orthotropic");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf1").set("usePlaneStressVersion", true);
    model.component("comp1").physics("shell").feature("llem1").feature().duplicate("lsf2", "lsf1");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf2")
         .label("\u5b89\u5168\u6027\uff1aHoffman \u51c6\u5219");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf2").set("FailureCriterion", "Hoffman");
    model.component("comp1").physics("shell").feature("llem1").feature().duplicate("lsf3", "lsf2");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf3")
         .label("\u5b89\u5168\u6027\uff1aTsai\u2013Hill\uff0c\u5e73\u9762\u5e94\u529b\u51c6\u5219");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf3").set("FailureCriterion", "Tsai-Hill");
    model.component("comp1").physics("shell").feature("llem1").feature().duplicate("lsf4", "lsf3");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf4")
         .label("\u5b89\u5168\u6027\uff1aAzzi\u2013Tsai\u2013Hill \u51c6\u5219");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf4")
         .set("FailureCriterion", "Azzi-Tsai-Hill");
    model.component("comp1").physics("shell").feature("llem1").feature().duplicate("lsf5", "lsf4");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf5")
         .label("\u5b89\u5168\u6027\uff1aNorris \u51c6\u5219");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf5").set("FailureCriterion", "Norris");
    model.component("comp1").physics("shell").feature("llem1").feature().duplicate("lsf6", "lsf5");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf6")
         .label("\u5b89\u5168\u6027\uff1aTsai\u2013Wu \u5404\u5411\u5f02\u6027\u51c6\u5219");
    model.component("comp1").physics("shell").feature("llem1").feature("lsf6")
         .set("FailureCriterion", "Tsai-Wu Anisotropic");

    model.material("mat1").propertyGroup()
         .create("OrthotropicStrengthParameters", "OrthotropicStrengthParameters", "Orthotropic_strength_parameters_in_Voigt_notation");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmats", new String[]{"Sigmats1", "Sigmats2", "Sigmats3"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmacs", new String[]{"Sigmacs1", "Sigmacs2", "Sigmacs3"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmass", new String[]{"Sigmass23", "Sigmass13", "Sigmass12"});
    model.material("mat1").propertyGroup()
         .create("AnisotropicStrengthParameters", "AnisotropicStrengthParameters", "Anisotropic_strength_parameters_in_Voigt_notation");
    model.material("mat1").propertyGroup("AnisotropicStrengthParameters")
         .set("F_s", new String[]{"1/Sigmats1-1/Sigmacs1", "1/Sigmats2-1/Sigmacs2", "1/Sigmats3-1/Sigmacs3", "0", "0", "0"});
    model.material("mat1").propertyGroup("AnisotropicStrengthParameters")
         .set("F_f", new String[]{"1/(Sigmats1*Sigmacs1)", "-0.5*sqrt(1/((Sigmats1*Sigmacs1)*(Sigmats2*Sigmacs2)))", "1/(Sigmats2*Sigmacs2)", "-0.5*sqrt(1/((Sigmats1*Sigmacs1)*(Sigmats3*Sigmacs3)))", "-0.5*sqrt(1/((Sigmats2*Sigmacs2)*(Sigmats3*Sigmacs3)))", "1/(Sigmats3*Sigmacs3)", "0", "0", "0", "1/Sigmass23^2", 
         "0", "0", "0", "0", "1/Sigmass13^2", "0", "0", "0", "0", "0", 
         "1/Sigmass12^2"});

    model.component("comp1").physics("shell").create("fix1", "Fixed", 0);
    model.component("comp1").physics("shell").feature("fix1").selection().set(1);
    model.component("comp1").physics("shell").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("shell").feature("disp1").selection().set(2);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("shell").feature("el1").selection().set(4);
    model.component("comp1").physics("shell").feature("el1").set("forceType", "TotalForce");
    model.component("comp1").physics("shell").feature("el1").set("force", new String[]{"Ftotal", "0", "0"});

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("scale", 10);
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u94fa\u5c42 1 \u4e2d\u7684\u5931\u6548\u6307\u6570");
    model.result().evaluationGroup("eg1").set("data", "lshl1");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1").selection().set(4);
    model.result().evaluationGroup("eg1").feature("pev1").set("locdef", "physical");
    model.result().evaluationGroup("eg1").feature("pev1").set("localzphys", "0.5*th");
    model.result().evaluationGroup("eg1").feature("pev1")
         .set("expr", new String[]{"mean(mean(shell.llem1.lsf1.f_i))", "mean(mean(shell.llem1.lsf2.f_i))", "mean(mean(shell.llem1.lsf3.f_i))", "mean(mean(shell.llem1.lsf4.f_i))", "mean(mean(shell.llem1.lsf5.f_i))", "mean(mean(shell.llem1.lsf6.f_i))", "mean(mean(shell.llem1.f_i_com))"});
    model.result().evaluationGroup("eg1").feature("pev1")
         .set("descr", new String[]{"Tsai-Wu \u6b63\u4ea4\u5404\u5411\u5f02\u6027\u5931\u6548\u6307\u6570", "Hoffman \u5931\u6548\u6307\u6570", "Tsai-Hill \u5931\u6548\u6307\u6570\uff0c\u5e73\u9762\u5e94\u529b", "Azzi-Tsai-Hill \u5931\u6548\u6307\u6570", "Norris \u5931\u6548\u6307\u6570", "Tsai-Wu \u5404\u5411\u5f02\u6027\u5931\u6548\u6307\u6570", "\u7efc\u5408\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").label("\u94fa\u5c42 2 \u4e2d\u7684\u5931\u6548\u6307\u6570");
    model.result().evaluationGroup("eg2").feature("pev1").set("localzphys", "1.5*th");
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().duplicate("eg3", "eg2");
    model.result().evaluationGroup("eg3").label("\u94fa\u5c42 3 \u4e2d\u7684\u5931\u6548\u6307\u6570");
    model.result().evaluationGroup("eg3").feature("pev1").set("localzphys", "2.5*th");
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup().duplicate("eg4", "eg3");
    model.result().evaluationGroup("eg4").label("\u94fa\u5c42 4 \u4e2d\u7684\u5931\u6548\u6307\u6570");
    model.result().evaluationGroup("eg4").feature("pev1").set("localzphys", "3.5*th");
    model.result().evaluationGroup("eg4").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "evaluationgroup");
    model.nodeGroup("grp1").add("evaluationgroup", "eg1");
    model.nodeGroup("grp1").add("evaluationgroup", "eg2");
    model.nodeGroup("grp1").add("evaluationgroup", "eg3");
    model.nodeGroup("grp1").add("evaluationgroup", "eg4");
    model.nodeGroup("grp1").label("\u5931\u6548\u6307\u6570");

    model.result().evaluationGroup().create("eg5", "EvaluationGroup");
    model.result().evaluationGroup("eg5").label("\u94fa\u5c42 1 \u4e2d\u7684\u5b89\u5168\u7cfb\u6570");
    model.result().evaluationGroup("eg5").set("data", "lshl1");
    model.result().evaluationGroup("eg5").set("transpose", true);
    model.result().evaluationGroup("eg5").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg5").feature("pev1").selection().set(4);
    model.result().evaluationGroup("eg5").feature("pev1").set("locdef", "physical");
    model.result().evaluationGroup("eg5").feature("pev1").set("localzphys", "0.5*th");
    model.result().evaluationGroup("eg5").feature("pev1")
         .set("expr", new String[]{"mean(mean(shell.llem1.lsf1.s_f))", "mean(mean(shell.llem1.lsf2.s_f))", "mean(mean(shell.llem1.lsf3.s_f))", "mean(mean(shell.llem1.lsf4.s_f))", "mean(mean(shell.llem1.lsf5.s_f))", "mean(mean(shell.llem1.lsf6.s_f))", "mean(mean(shell.llem1.s_f_com))"});
    model.result().evaluationGroup("eg5").feature("pev1")
         .set("descr", new String[]{"Tsai-Wu \u6b63\u4ea4\u5404\u5411\u5f02\u6027\u5b89\u5168\u7cfb\u6570", "Hoffman \u5b89\u5168\u7cfb\u6570", "Tsai-Hill \u5b89\u5168\u7cfb\u6570\uff0c\u5e73\u9762\u5e94\u529b", "Azzi-Tsai-Hill \u5b89\u5168\u7cfb\u6570", "Norris \u5b89\u5168\u7cfb\u6570", "Tsai-Wu \u5404\u5411\u5f02\u6027\u5b89\u5168\u7cfb\u6570", "\u7efc\u5408\u5b89\u5168\u7cfb\u6570"});
    model.result().evaluationGroup("eg5").run();
    model.result().evaluationGroup().duplicate("eg6", "eg5");
    model.result().evaluationGroup("eg6").label("\u94fa\u5c42 2 \u4e2d\u7684\u5b89\u5168\u7cfb\u6570");
    model.result().evaluationGroup("eg6").feature("pev1").set("localzphys", "1.5*th");
    model.result().evaluationGroup("eg6").run();
    model.result().evaluationGroup().duplicate("eg7", "eg6");
    model.result().evaluationGroup("eg7").label("\u94fa\u5c42 3 \u4e2d\u7684\u5b89\u5168\u7cfb\u6570");
    model.result().evaluationGroup("eg7").feature("pev1").set("localzphys", "2.5*th");
    model.result().evaluationGroup("eg7").run();
    model.result().evaluationGroup().duplicate("eg8", "eg7");
    model.result().evaluationGroup("eg8").label("\u94fa\u5c42 4 \u4e2d\u7684\u5b89\u5168\u7cfb\u6570");
    model.result().evaluationGroup("eg8").feature("pev1").set("localzphys", "3.5*th");
    model.result().evaluationGroup("eg8").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "evaluationgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("evaluationgroup", "eg5");
    model.nodeGroup("grp2").add("evaluationgroup", "eg6");
    model.nodeGroup("grp2").add("evaluationgroup", "eg7");
    model.nodeGroup("grp2").add("evaluationgroup", "eg8");
    model.nodeGroup("grp2").label("\u5b89\u5168\u7cfb\u6570");

    model.result().evaluationGroup().create("eg9", "EvaluationGroup");
    model.result().evaluationGroup("eg9").label("\u94fa\u5c42 1 \u4e2d\u7684\u5e94\u529b");
    model.result().evaluationGroup("eg9").set("data", "lshl1");
    model.result().evaluationGroup("eg9").set("transpose", true);
    model.result().evaluationGroup("eg9").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg9").feature("pev1").selection().set(4);
    model.result().evaluationGroup("eg9").feature("pev1").set("locdef", "physical");
    model.result().evaluationGroup("eg9").feature("pev1").set("localzphys", "0.5*th");
    model.result().evaluationGroup("eg9").feature("pev1")
         .set("expr", new String[]{"mean(mean(shell.Sll11))", "mean(mean(shell.Sll22))", "mean(mean(shell.Sll12))"});
    model.result().evaluationGroup("eg9").feature("pev1").set("unit", new String[]{"MPa", "MPa", "MPa"});
    model.result().evaluationGroup("eg9").feature("pev1")
         .set("descr", new String[]{"\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0c\u5c42\u5750\u6807\u7cfb\uff0c11 \u5206\u91cf", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0c\u5c42\u5750\u6807\u7cfb\uff0c22 \u5206\u91cf", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0c\u5c42\u5750\u6807\u7cfb\uff0c12 \u5206\u91cf"});
    model.result().evaluationGroup("eg9").run();
    model.result().evaluationGroup().duplicate("eg10", "eg9");
    model.result().evaluationGroup("eg10").label("\u94fa\u5c42 2 \u4e2d\u7684\u5e94\u529b");
    model.result().evaluationGroup("eg10").feature("pev1").set("localzphys", "1.5*th");
    model.result().evaluationGroup("eg10").run();
    model.result().evaluationGroup().duplicate("eg11", "eg10");
    model.result().evaluationGroup("eg11").label("\u94fa\u5c42 3 \u4e2d\u7684\u5e94\u529b");
    model.result().evaluationGroup("eg11").feature("pev1").set("localzphys", "2.5*th");
    model.result().evaluationGroup("eg11").run();
    model.result().evaluationGroup().duplicate("eg12", "eg11");
    model.result().evaluationGroup("eg12").label("\u94fa\u5c42 4 \u4e2d\u7684\u5e94\u529b");
    model.result().evaluationGroup("eg12").feature("pev1").set("localzphys", "3.5*th");
    model.result().evaluationGroup("eg12").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "evaluationgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("evaluationgroup", "eg9");
    model.nodeGroup("grp3").add("evaluationgroup", "eg10");
    model.nodeGroup("grp3").add("evaluationgroup", "eg11");
    model.nodeGroup("grp3").add("evaluationgroup", "eg12");
    model.nodeGroup("grp3").label("\u5e94\u529b");

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("von Mises \u5e94\u529b\uff08\u94fa\u5c42\uff09");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u591a\u5c42\u6750\u6599\u5207\u9762\uff1avon Mises \u5e94\u529b (MPa)");
    model.result("pg1").set("legendpos", "rightdouble");
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("arrayaxis", "z");
    model.result("pg1").set("displacementlinear", "absolute");
    model.result("pg1").set("celldisp", "3E4*th");
    model.result("pg1").create("lss1", "LayeredMaterialSlice");
    model.result("pg1").feature("lss1").set("arraydim", "1");
    model.result("pg1").feature("lss1").label("\u94fa\u5c42 1");
    model.result("pg1").feature("lss1").set("expr", "shell.misesGp");
    model.result("pg1").feature("lss1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg1").feature("lss1").set("expr", "round(shell.mises)");
    model.result("pg1").feature("lss1").set("locdef", "relative");
    model.result("pg1").feature("lss1").set("localzrel", -0.75);
    model.result("pg1").feature().duplicate("lss2", "lss1");
    model.result("pg1").feature("lss2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("lss2").label("\u94fa\u5c42 2");
    model.result("pg1").feature("lss2").set("localzrel", -0.25);
    model.result("pg1").feature("lss2").set("titletype", "none");
    model.result("pg1").feature("lss2").set("colortable", "Cyclic");
    model.result("pg1").feature().duplicate("lss3", "lss2");
    model.result("pg1").feature("lss3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("lss3").label("\u94fa\u5c42 3");
    model.result("pg1").feature("lss3").set("localzrel", 0.25);
    model.result("pg1").feature("lss3").set("colortable", "Disco");
    model.result("pg1").feature().duplicate("lss4", "lss3");
    model.result("pg1").feature("lss4").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("lss4").label("\u94fa\u5c42 4");
    model.result("pg1").feature("lss4").set("localzrel", 0.75);
    model.result("pg1").feature("lss4").set("colortable", "ThermalDark");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", true);

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("Hoffman \u5b89\u5168\u7cfb\u6570\uff08\u94fa\u5c42\uff09");
    model.result("pg2")
         .set("title", "\u591a\u5c42\u6750\u6599\u5207\u9762\uff1aHoffman \u5b89\u5168\u7cfb\u6570 (1)");
    model.result("pg2").feature("lss1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("lss1").set("expr", "shell.llem1.lsf2.s_f");
    model.result("pg2").feature("lss1").set("descr", "Hoffman \u5b89\u5168\u7cfb\u6570");
    model.result("pg2").feature("lss2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("lss2").set("expr", "shell.llem1.lsf2.s_f");
    model.result("pg2").feature("lss2").set("descr", "Hoffman \u5b89\u5168\u7cfb\u6570");
    model.result("pg2").feature("lss3").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("lss3").set("expr", "shell.llem1.lsf2.s_f");
    model.result("pg2").feature("lss3").set("descr", "Hoffman \u5b89\u5168\u7cfb\u6570");
    model.result("pg2").feature("lss4").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("lss4").set("expr", "shell.llem1.lsf2.s_f");
    model.result("pg2").feature("lss4").set("descr", "Hoffman \u5b89\u5168\u7cfb\u6570");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("von Mises \u5e94\u529b\uff08\u5c42\u5408\u677f\uff09");
    model.result("pg3").set("data", "lshl1");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "shell.misesGp");
    model.result("pg3").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg3").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg3").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", "0.5e-2");
    model.result().dataset("cpt1").set("pointy", "0.5e-2");
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u4e2d\u70b9\u7684 von Mises \u5e94\u529b\uff08\u5168\u539a\u5ea6\uff09");
    model.result("pg4").set("data", "cpt1");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("thr1", "ThroughThickness");
    model.result("pg4").feature("thr1").set("markerpos", "datapoints");
    model.result("pg4").feature("thr1").set("linewidth", "preference");
    model.result("pg4").feature("thr1").set("expr", "shell.mises");
    model.result("pg4").feature("thr1").set("thicknesscoordinateunit", "mm");
    model.result("pg4").run();
    model.result("pg2").run();

    model.title("\u5c42\u538b\u590d\u5408\u58f3\u7684\u5931\u6548\u9884\u6d4b");

    model
         .description("\u7531\u78b3\u7ea4\u7ef4\u589e\u5f3a\u805a\u5408\u7269 (CFRP) \u5236\u6210\u7684\u5c42\u538b\u590d\u5408\u58f3\u56e0\u5176\u9ad8\u5f3a\u5ea6\u91cd\u91cf\u6bd4\u800c\u5e7f\u6cdb\u7528\u4e8e\u5404\u79cd\u5e94\u7528\u3002\u8bc4\u4f30\u5c42\u538b\u590d\u5408\u58f3\u5728\u4e00\u7cfb\u5217\u5916\u52a0\u8f7d\u8377\u4f5c\u7528\u4e0b\u7684\u7ed3\u6784\u5b8c\u6574\u6027\u662f\u786e\u4fdd\u8fd9\u79cd\u7ed3\u6784\u8bbe\u8ba1\u53ef\u9760\u7684\u5fc5\u8981\u6761\u4ef6\u3002\u672c\u4f8b\u91c7\u7528\u4e0d\u540c\u7684\u591a\u9879\u5f0f\u5931\u6548\u51c6\u5219\uff0c\u901a\u8fc7\u201c\u5931\u6548\u6307\u6570\u201d\u548c\u201c\u5b89\u5168\u7cfb\u6570\u201d\u5bf9\u6bcf\u4e2a\u94fa\u5c42\u5177\u6709\u4e0d\u540c\u7ea4\u7ef4\u65b9\u5411\u7684\u5c42\u5408\u677f\u7684\u7ed3\u6784\u5b8c\u6574\u6027\u8fdb\u884c\u8bc4\u4f30\u3002\n\n\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5c06\u201c\u58f3\u201d\u63a5\u53e3\u4e0e\u201c\u590d\u5408\u6750\u6599\u6a21\u5757\u201d\u7ed3\u5408\u4f7f\u7528\uff0c\u4ee5\u5bf9\u5c42\u538b\u590d\u5408\u58f3\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u6bd4\u8f83\u4e86\u516d\u79cd\u4e0d\u540c\u7684\u591a\u9879\u5f0f\u51c6\u5219\u3002\u8fd9\u662f\u4e00\u4e2a NAFEMS \u57fa\u51c6\u6a21\u578b\uff0c\u5176\u4e2d\u5c06\u8ba1\u7b97\u7ed3\u679c\u4e0e\u53c2\u8003\u6570\u636e\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("failure_prediction_in_a_laminated_composite_shell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
