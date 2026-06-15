/*
 * stacking_sequence_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:35 by COMSOL 6.3.0.290. */
public class stacking_sequence_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a", "0.5[m]", "\u8fb9\u957f");
    model.param().set("d_layer", "1[mm]", "\u8584\u5c42\u539a\u5ea6");
    model.param().set("th1", "0[deg]", "\u7ea4\u7ef4\u65b9\u5411\uff0c\u5c42 1");
    model.param().set("th2", "0[deg]", "\u7ea4\u7ef4\u65b9\u5411\uff0c\u5c42 2");
    model.param().set("th3", "0[deg]", "\u7ea4\u7ef4\u65b9\u5411\uff0c\u5c42 3");
    model.param().set("F", "2[kN]", "\u5916\u52a0\u8f7d\u8377");
    model.param().set("pExp", "10", "P \u8303\u6570");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup()
         .create("OrthotropicStrengthParameters", "OrthotropicStrengthParameters", "Orthotropic strength parameters, Voigt notation");
    model.material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.material("mat1")
         .label("Unidirectional fiber lamina: AS4/APC2 carbon/PEEK thermoplastic [fiber volume fraction 58%]");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("density", "1570[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.2E-6[1/K]", "0", "0", "0", "24E-6[1/K]", "0", "0", "0", "24E-6[1/K]"});
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .label("Orthotropic strength parameters, Voigt notation");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmats", new String[]{"2060[MPa]", "78[MPa]", "78[MPa]"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmats", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmacs", new String[]{"1590[MPa]", "200[MPa]", "200[MPa]"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmacs", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .set("sigmass", new String[]{"157[MPa]", "157[MPa]", "157[MPa]"});
    model.material("mat1").propertyGroup("OrthotropicStrengthParameters")
         .setPropertyInfo("sigmass", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("TransverseIsotropic").label("Transversely isotropic");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"138[GPa]", "8.7[GPa]"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.28", "0.45"});
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", "5[GPa]");
    model.material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("rotation", "th1", 0);
    model.material("lmat1").setIndex("thickness", "d_layer", 0);
    model.material("lmat1").setIndex("meshPoints", 1, 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "th1", 1);
    model.material("lmat1").setIndex("thickness", "d_layer", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "th1", 1);
    model.material("lmat1").setIndex("thickness", "d_layer", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("rotation", "th2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", "th2", 2);
    model.material("lmat1").setIndex("thickness", "d_layer", 2);
    model.material("lmat1").setIndex("meshPoints", 1, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", "th2", 2);
    model.material("lmat1").setIndex("thickness", "d_layer", 2);
    model.material("lmat1").setIndex("meshPoints", 1, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("rotation", "th3", 2);
    model.material("lmat1").label("\u591a\u5c42\u6750\u6599\uff1a[th1/th2/th3]");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "a");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");
    model.component("comp1").material("llmat1").set("transform", "symmetry");
    model.component("comp1").material("llmat1").set("widthRatio", 0.4);

    model.component("comp1").physics("lshell").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("lshell").feature("lemm1").create("sf1", "Safety", 2);
    model.component("comp1").physics("lshell").feature("lemm1").feature("sf1")
         .set("FailureCriterion", "Hashin Orthotropic");
    model.component("comp1").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("lshell").feature("fix1").selection().set(1);
    model.component("comp1").physics("lshell").create("ll1", "LineLoad", 0);
    model.component("comp1").physics("lshell").feature("ll1").selection().set(4);
    model.component("comp1").physics("lshell").feature("ll1").set("forceType", "TotalForce");
    model.component("comp1").physics("lshell").feature("ll1").set("force", new String[]{"0", "0", "F"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().all();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("FI_max_l1", "intop1(lshell.atxd1(0*d_layer,lshell.lemm1.sf1.f_i)^pExp)");
    model.component("comp1").variable("var1")
         .descr("FI_max_l1", "\u6700\u5927\u5931\u6548\u6307\u6570\uff0c\u7b2c 1 \u5c42");
    model.component("comp1").variable("var1")
         .set("FI_max_l2", "intop1(lshell.atxd1(1*d_layer,lshell.lemm1.sf1.f_i)^pExp)");
    model.component("comp1").variable("var1")
         .descr("FI_max_l2", "\u6700\u5927\u5931\u6548\u6307\u6570\uff0c\u7b2c 2 \u5c42");
    model.component("comp1").variable("var1")
         .set("FI_max_l3", "intop1(lshell.atxd1(2*d_layer,lshell.lemm1.sf1.f_i)^pExp)");
    model.component("comp1").variable("var1")
         .descr("FI_max_l3", "\u6700\u5927\u5931\u6548\u6307\u6570\uff0c\u7b2c 3 \u5c42");
    model.component("comp1").variable("var1")
         .set("FI_max_l4", "intop1(lshell.atxd1(3*d_layer,lshell.lemm1.sf1.f_i)^pExp)");
    model.component("comp1").variable("var1")
         .descr("FI_max_l4", "\u6700\u5927\u5931\u6548\u6307\u6570\uff0c\u7b2c 4 \u5c42");
    model.component("comp1").variable("var1")
         .set("FI_max_l5", "intop1(lshell.atxd1(4*d_layer,lshell.lemm1.sf1.f_i)^pExp)");
    model.component("comp1").variable("var1")
         .descr("FI_max_l5", "\u6700\u5927\u5931\u6548\u6307\u6570\uff0c\u7b2c 5 \u5c42");
    model.component("comp1").variable("var1")
         .set("FI_max_l6", "intop1(lshell.atxd1(5*d_layer,lshell.lemm1.sf1.f_i)^pExp)");
    model.component("comp1").variable("var1")
         .descr("FI_max_l6", "\u6700\u5927\u5931\u6548\u6307\u6570\uff0c\u7b2c 6 \u5c42");
    model.component("comp1").variable("var1")
         .set("FI_max_l7", "intop1(lshell.atxd1(6*d_layer,lshell.lemm1.sf1.f_i)^pExp)");
    model.component("comp1").variable("var1")
         .set("FI_max", "((FI_max_l1+FI_max_l2+FI_max_l3+FI_max_l4+FI_max_l5+FI_max_l6+FI_max_l7)/intop1(7))^(1/pExp)");
    model.component("comp1").variable("var1").descr("FI_max", "\u6700\u5927\u5931\u6548\u6307\u6570");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").run("map1");

    model.study("std1").label("\u7814\u7a76 1\uff1a\u539f\u59cb\u94fa\u5c42");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1lshelllshl", "LayeredMaterial");
    model.result().dataset("dset1lshelllshl").set("data", "dset1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1lshelllshl");
    model.result("pg1").label("\u5e94\u529b (lshell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1")
         .label("\u6700\u5927\u5931\u6548\u6307\u6570\uff08\u539f\u59cb\u503c\uff09");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "FI_max", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2")
         .label("\u4e0d\u540c\u5931\u6548\u6a21\u5f0f\u7684\u5931\u6548\u6307\u6570\uff08\u539f\u59cb\u503c\uff09");
    model.result().evaluationGroup("eg2").set("data", "dset1lshelllshl");
    model.result().evaluationGroup("eg2").set("transpose", true);
    model.result().evaluationGroup("eg2").create("max1", "MaxVolume");
    model.result().evaluationGroup("eg2").feature("max1").set("expr", new String[]{"lshell.lemm1.sf1.f_ifT"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT", "lshell.lemm1.sf1.f_imC"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u538b\u7f29\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT", "lshell.lemm1.sf1.f_imC", "lshell.lemm1.sf1.f_iiT"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u62c9\u4f38\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT", "lshell.lemm1.sf1.f_imC", "lshell.lemm1.sf1.f_iiT", "lshell.lemm1.sf1.f_iiC"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u538b\u7f29\u5931\u6548\u6307\u6570"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT", "lshell.lemm1.sf1.f_imC", "lshell.lemm1.sf1.f_iiT", "lshell.lemm1.sf1.f_iiC", "th1"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u538b\u7f29\u5931\u6548\u6307\u6570", "\u7ea4\u7ef4\u65b9\u5411\uff0c\u5c42 1"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT", "lshell.lemm1.sf1.f_imC", "lshell.lemm1.sf1.f_iiT", "lshell.lemm1.sf1.f_iiC", "th1", "th2"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u538b\u7f29\u5931\u6548\u6307\u6570", "\u7ea4\u7ef4\u65b9\u5411\uff0c\u5c42 1", "\u7ea4\u7ef4\u65b9\u5411\uff0c\u5c42 2"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("expr", new String[]{"lshell.lemm1.sf1.f_ifT", "lshell.lemm1.sf1.f_ifC", "lshell.lemm1.sf1.f_imT", "lshell.lemm1.sf1.f_imC", "lshell.lemm1.sf1.f_iiT", "lshell.lemm1.sf1.f_iiC", "th1", "th2", "th3"});
    model.result().evaluationGroup("eg2").feature("max1")
         .set("descr", new String[]{"Hashin \u7ea4\u7ef4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u7ea4\u7ef4\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u57fa\u4f53\u538b\u7f29\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u62c9\u4f38\u5931\u6548\u6307\u6570", "Hashin \u5c42\u95f4\u538b\u7f29\u5931\u6548\u6307\u6570", "\u7ea4\u7ef4\u65b9\u5411\uff0c\u5c42 1", "\u7ea4\u7ef4\u65b9\u5411\uff0c\u5c42 2", "\u7ea4\u7ef4\u65b9\u5411\uff0c\u5c42 3"});
    model.result().evaluationGroup("eg2").feature("max1").setIndex("unit", "deg", 6);
    model.result().evaluationGroup("eg2").feature("max1").setIndex("unit", "deg", 7);
    model.result().evaluationGroup("eg2").feature("max1").setIndex("unit", "deg", 8);
    model.result().evaluationGroup("eg2").run();
    model.result().dataset("dset1lshelllshl").set("scale", 10);
    model.result("pg1").run();
    model.result("pg1").label("von Mises \u5e94\u529b\uff08\u539f\u59cb\u503c\uff09");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").create("lss1", "LayeredMaterialSlice");
    model.result("pg2").feature("lss1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg2").feature("lss1").set("threshold", "manual");
    model.result("pg2").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("lss1").set("colortable", "Prism");
    model.result("pg2").feature("lss1").set("colortabletrans", "none");
    model.result("pg2").feature("lss1").set("colorscalemode", "linear");
    model.result("pg2").feature("lss1").set("locdef", "relative");
    model.result("pg2").feature("lss1").set("localzrel", "lshell.z");
    model.result("pg2").feature("lss1").create("def", "Deform");
    model.result("pg2").feature("lss1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("lss1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg2").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg2").run();
    model.result("pg2").label("\u5931\u6548\u6307\u6570\uff0c\u5207\u9762\uff08\u539f\u59cb\u503c\uff09");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").run();
    model.result("pg2").feature("lss1").set("expr", "lshell.lemm1.sf1.f_i");
    model.result("pg2").feature("lss1").set("descr", "Hashin \u5931\u6548\u6307\u6570");
    model.result("pg2").feature("lss1").set("locdef", "interfaces");
    model.result("pg2").feature("lss1").set("slicedisplacement", "rectangular");
    model.result("pg2").feature("lss1").set("xseparation", 0.4);
    model.result("pg2").feature("lss1").set("yseparation", 0.4);
    model.result("pg2").feature("lss1").set("showdescriptions", true);
    model.result("pg2").feature("lss1").set("descriptionseparation", 0.5);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("view", "new");
    model.result("pg2").run();

    model.view("view4").set("showgrid", false);

    model.result().dataset().create("dset1lshelllshlge", "LayeredMaterial");
    model.result().dataset("dset1lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().dataset("dset1lshelllshlge").set("data", "dset1");
    model.result().dataset("dset1lshelllshlge").set("scale", "50*0.07071067811865477");
    model.result().dataset("dset1lshelllshlge")
         .set("defaultPlotIDs", new String[]{"shellGeometry|lshell", "plyAngle|lshell"});
    model.result().dataset("dset1lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1lshelllshlge");
    model.result("pg3").label("\u94fa\u5c42\u89d2\u5ea6 (lshell)");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").set("edgecolor", "white");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"lshell.rot"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("unit", "deg");
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", -90);
    model.result("pg3").feature("surf1").set("rangecolormax", 90);
    model.result("pg3").label("\u94fa\u5c42\u89d2\u5ea6 (lshell)");
    model.result("pg3").run();
    model.result().dataset().create("dset1lshelllshlfi", "LayeredMaterial");
    model.result().dataset("dset1lshelllshlfi").label("\u591a\u5c42\u6750\u6599 2 (\u6750\u6599\u65b9\u5411)");
    model.result().dataset("dset1lshelllshlfi").set("data", "dset1");
    model.result().dataset("dset1lshelllshlfi").set("evaluatein", "layermidplanes");
    model.result().dataset("dset1lshelllshlfi").set("scale", "200*0.07071067811865477");
    model.result().dataset("dset1lshelllshlfi")
         .set("defaultPlotIDs", new String[]{"firstPrincipalMaterialDirection|lshell"});
    model.result().dataset("dset1lshelllshlfi").label("\u591a\u5c42\u6750\u6599 2 (\u6750\u6599\u65b9\u5411)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1lshelllshlfi");
    model.result("pg4").label("\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411 (lshell)");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("edgecolor", "white");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"lshell.rot"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("unit", "deg");
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", -90);
    model.result("pg4").feature("surf1").set("rangecolormax", 90);
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"lshell.tm11", "lshell.tm12", "lshell.tm13"});
    model.result("pg4").feature("arws1").set("placement", "elements");
    model.result("pg4").feature("arws1").set("arrowtype", "cone");
    model.result("pg4").feature("arws1").set("color", "white");
    model.result("pg4").label("\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411 (lshell)");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().dataset("dset1lshelllshlge").set("scale", 40);
    model.result("pg3").run();
    model.result("pg3").label("\u94fa\u5c42\u89d2\u5ea6\uff08\u539f\u59cb\uff09");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().dataset("dset1lshelllshlfi").set("scale", 40);
    model.result("pg4").run();
    model.result("pg4").label("\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411\uff08\u539f\u59cb\uff09");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411\uff08\u539f\u59cb\uff09th1=eval(th1*180/pi,,%1.1f) deg, th2=eval(th2*180/pi,,%1.1f) deg, th3=eval(th3*180/pi,,%1.1f) deg");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1lshelllshl");
    model.result("pg5").label("\u7ebf\u8f7d\u8377 (lshell)");
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
    model.result("pg5").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg5").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def").set("scale", 0);
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg5").feature("surf1").create("tran1", "Transparency");
    model.result("pg5").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg5").create("arpt1", "ArrowPoint");
    model.result("pg5").feature("arpt1")
         .set("expr", new String[]{"lshell.ll1.flx", "lshell.ll1.fly", "lshell.ll1.flz"});
    model.result("pg5").feature("arpt1").set("arrowbase", "tail");
    model.result("pg5").feature("arpt1").label("\u7ebf\u8f7d\u8377 1");
    model.result("pg5").feature("arpt1").set("inheritplot", "none");
    model.result("pg5").feature("arpt1").create("col", "Color");
    model.result("pg5").feature("arpt1").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("arpt1").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("arpt1").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("arpt1").feature("col").set("colordata", "arrowlength");
    model.result("pg5").feature("arpt1").feature("col").set("coloring", "gradient");
    model.result("pg5").feature("arpt1").feature("col").set("topcolor", "red");
    model.result("pg5").feature("arpt1").feature("col").set("bottomcolor", "custom");
    model.result("pg5").feature("arpt1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg5").feature("arpt1").set("color", "green");
    model.result("pg5").feature("arpt1").create("def", "Deform");
    model.result("pg5").feature("arpt1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("arpt1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg5").feature("arpt1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("arpt1").feature("def").set("scale", 0);
    model.result("pg5").label("\u7ebf\u8f7d\u8377 (lshell)");
    model.result("pg5").run();
    model.result("pg5").label("\u7ebf\u8f7d\u8377\uff08\u539f\u59cb\uff09");
    model.result("pg5").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/lshell", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u94fa\u5c42\u4f18\u5316");
    model.study("std2").create("opt", "Optimization");
    model.study("std2").feature("opt").set("optsolver", "bobyqa");
    model.study("std2").feature("opt").set("opttol", "0.0001");
    model.study("std2").feature("opt").set("optobj", new String[]{"comp1.FI_max"});
    model.study("std2").feature("opt").set("descr", new String[]{"\u6700\u5927\u5931\u6548\u6307\u6570"});
    model.study("std2").feature("opt").setIndex("pname", "a", 0);
    model.study("std2").feature("opt").setIndex("initval", "0.5[m]", 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "a", 0);
    model.study("std2").feature("opt").setIndex("initval", "0.5[m]", 0);
    model.study("std2").feature("opt").setIndex("scale", 1, 0);
    model.study("std2").feature("opt").setIndex("lbound", "", 0);
    model.study("std2").feature("opt").setIndex("ubound", "", 0);
    model.study("std2").feature("opt").setIndex("pname", "d_layer", 1);
    model.study("std2").feature("opt").setIndex("initval", "1[mm]", 1);
    model.study("std2").feature("opt").setIndex("scale", 1, 1);
    model.study("std2").feature("opt").setIndex("lbound", "", 1);
    model.study("std2").feature("opt").setIndex("ubound", "", 1);
    model.study("std2").feature("opt").setIndex("pname", "d_layer", 1);
    model.study("std2").feature("opt").setIndex("initval", "1[mm]", 1);
    model.study("std2").feature("opt").setIndex("scale", 1, 1);
    model.study("std2").feature("opt").setIndex("lbound", "", 1);
    model.study("std2").feature("opt").setIndex("ubound", "", 1);
    model.study("std2").feature("opt").setIndex("pname", "th1", 2);
    model.study("std2").feature("opt").setIndex("initval", "0[deg]", 2);
    model.study("std2").feature("opt").setIndex("scale", 1, 2);
    model.study("std2").feature("opt").setIndex("lbound", "", 2);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("opt").setIndex("ubound", "", 2);
    model.study("std2").feature("opt").setIndex("pname", "th1", 2);
    model.study("std2").feature("opt").setIndex("initval", "0[deg]", 2);
    model.study("std2").feature("opt").setIndex("scale", 1, 2);
    model.study("std2").feature("opt").setIndex("lbound", "", 2);
    model.study("std2").feature("opt").setIndex("ubound", "", 2);
    model.study("std2").feature("opt").setIndex("pname", "", 0);
    model.study("std2").feature("opt").setIndex("initval", "0[deg]", 0);
    model.study("std2").feature("opt").setIndex("scale", "45[deg]", 0);
    model.study("std2").feature("opt").setIndex("lbound", "-90[deg]", 0);
    model.study("std2").feature("opt").setIndex("ubound", "90[deg]", 0);
    model.study("std2").feature("opt").setIndex("pname", "th2", 1);
    model.study("std2").feature("opt").setIndex("scale", "45[deg]", 1);
    model.study("std2").feature("opt").setIndex("lbound", "-90[deg]", 1);
    model.study("std2").feature("opt").setIndex("ubound", "90[deg]", 1);
    model.study("std2").feature("opt").setIndex("pname", "th3", 2);
    model.study("std2").feature("opt").setIndex("pname", "th1", 0);
    model.study("std2").feature("opt").setIndex("scale", "45[deg]", 2);
    model.study("std2").feature("opt").setIndex("lbound", "-90[deg]", 2);
    model.study("std2").feature("opt").setIndex("ubound", "90[deg]", 2);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("o1").run("compute");

    model.result().dataset().create("dset3lshelllshl", "LayeredMaterial");
    model.result().dataset("dset3lshelllshl").set("data", "dset3");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset3lshelllshl");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").label("\u5e94\u529b (lshell)");
    model.result("pg6").set("showlegends", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg6").run();

    model.study("std2").feature("opt").set("probewindow", "");

    model.result().dataset("dset3lshelllshl").set("scale", 10);
    model.result("pg6").run();
    model.result("pg6").label("von Mises \u5e94\u529b\uff08\u4f18\u5316\u503c\uff09");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg7").set("showlegends", true);
    model.result("pg7").create("lss1", "LayeredMaterialSlice");
    model.result("pg7").feature("lss1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg7").feature("lss1").set("threshold", "manual");
    model.result("pg7").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("lss1").set("colortable", "Prism");
    model.result("pg7").feature("lss1").set("colortabletrans", "none");
    model.result("pg7").feature("lss1").set("colorscalemode", "linear");
    model.result("pg7").feature("lss1").set("locdef", "relative");
    model.result("pg7").feature("lss1").set("localzrel", "lshell.z");
    model.result("pg7").feature("lss1").create("def", "Deform");
    model.result("pg7").feature("lss1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("lss1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg7").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg7").run();
    model.result("pg7").label("\u5931\u6548\u6307\u6570\uff0c\u5207\u9762\uff08\u4f18\u5316\u503c\uff09");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").run();
    model.result("pg7").feature("lss1").set("expr", "lshell.lemm1.sf1.f_i");
    model.result("pg7").feature("lss1").set("descr", "Hashin \u5931\u6548\u6307\u6570");
    model.result("pg7").feature("lss1").set("locdef", "interfaces");
    model.result("pg7").feature("lss1").set("slicedisplacement", "rectangular");
    model.result("pg7").feature("lss1").set("xseparation", 0.4);
    model.result("pg7").feature("lss1").set("yseparation", 0.4);
    model.result("pg7").feature("lss1").set("showdescriptions", true);
    model.result("pg7").feature("lss1").set("descriptionseparation", 0.5);
    model.result("pg7").run();
    model.result("pg7").set("view", "view4");
    model.result("pg7").run();
    model.result().dataset().create("dset3lshelllshlge", "LayeredMaterial");
    model.result().dataset("dset3lshelllshlge").label("\u591a\u5c42\u6750\u6599 3 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().dataset("dset3lshelllshlge").set("data", "dset3");
    model.result().dataset("dset3lshelllshlge").set("scale", "50*0.07071067811865477");
    model.result().dataset("dset3lshelllshlge")
         .set("defaultPlotIDs", new String[]{"shellGeometry|lshell", "plyAngle|lshell"});
    model.result().dataset("dset3lshelllshlge").label("\u591a\u5c42\u6750\u6599 3 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset3lshelllshlge");
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").label("\u94fa\u5c42\u89d2\u5ea6 (lshell)");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("showlegends", true);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").set("edgecolor", "white");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"lshell.rot"});
    model.result("pg8").feature("surf1").set("threshold", "manual");
    model.result("pg8").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("surf1").set("colortabletrans", "none");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").feature("surf1").set("unit", "deg");
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("rangecoloractive", true);
    model.result("pg8").feature("surf1").set("rangecolormin", -90);
    model.result("pg8").feature("surf1").set("rangecolormax", 90);
    model.result("pg8").label("\u94fa\u5c42\u89d2\u5ea6 (lshell)");
    model.result("pg8").run();
    model.result().dataset().create("dset3lshelllshlfi", "LayeredMaterial");
    model.result().dataset("dset3lshelllshlfi").label("\u591a\u5c42\u6750\u6599 3 (\u6750\u6599\u65b9\u5411)");
    model.result().dataset("dset3lshelllshlfi").set("data", "dset3");
    model.result().dataset("dset3lshelllshlfi").set("evaluatein", "layermidplanes");
    model.result().dataset("dset3lshelllshlfi").set("scale", "200*0.07071067811865477");
    model.result().dataset("dset3lshelllshlfi")
         .set("defaultPlotIDs", new String[]{"firstPrincipalMaterialDirection|lshell"});
    model.result().dataset("dset3lshelllshlfi").label("\u591a\u5c42\u6750\u6599 3 (\u6750\u6599\u65b9\u5411)");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset3lshelllshlfi");
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").label("\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411 (lshell)");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("edgecolor", "white");
    model.result("pg9").set("showlegends", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"lshell.rot"});
    model.result("pg9").feature("surf1").set("threshold", "manual");
    model.result("pg9").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg9").feature("surf1").set("colortabletrans", "none");
    model.result("pg9").feature("surf1").set("colorscalemode", "linear");
    model.result("pg9").feature("surf1").set("unit", "deg");
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").feature("surf1").set("rangecoloractive", true);
    model.result("pg9").feature("surf1").set("rangecolormin", -90);
    model.result("pg9").feature("surf1").set("rangecolormax", 90);
    model.result("pg9").create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").set("expr", new String[]{"lshell.tm11", "lshell.tm12", "lshell.tm13"});
    model.result("pg9").feature("arws1").set("placement", "elements");
    model.result("pg9").feature("arws1").set("arrowtype", "cone");
    model.result("pg9").feature("arws1").set("color", "white");
    model.result("pg9").label("\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411 (lshell)");
    model.result("pg9").run();
    model.result("pg8").run();
    model.result().dataset("dset3lshelllshlge").set("scale", 40);
    model.result("pg8").run();
    model.result("pg8").label("\u94fa\u5c42\u89d2\u5ea6\uff08\u4f18\u5316\uff09");
    model.result("pg8").run();
    model.result("pg9").run();
    model.result().dataset("dset3lshelllshlfi").set("scale", 40);
    model.result("pg9").run();
    model.result("pg9").label("\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411\uff08\u4f18\u5316\uff09");
    model.result("pg9").set("titletype", "manual");
    model.result("pg9")
         .set("title", "\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411\uff08\u4f18\u5316\uff09\uff0cth1=eval(th1*180/pi,,%1.1f) deg, th2=eval(th2*180/pi,,%1.1f) deg, th3=eval(th3*180/pi,,%1.1f) deg");
    model.result("pg9").set("paramindicator", "");
    model.result("pg9").run();
    model.result("pg2").run();
    model.result().duplicate("pg10", "pg2");
    model.result("pg10").run();
    model.result("pg10").set("edges", false);
    model.result("pg10").run();
    model.result("pg10").feature("lss1").set("data", "dset1");
    model.result("pg10").feature("lss1").set("expr", "lshell.disp");
    model.result("pg10").feature("lss1").set("locdef", "refsurf");
    model.result("pg10").feature("lss1").set("slicedisplacement", "none");
    model.result("pg10").feature("lss1").set("showdescriptions", false);
    model.result("pg10").feature("lss1").set("colortable", "Twilight");
    model.result("pg10").feature("lss1").set("wireframe", true);
    model.result("pg10").feature().duplicate("lss2", "lss1");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("lss1").feature("def").set("scaleactive", true);
    model.result("pg10").feature("lss1").feature("def").set("scale", 1);
    model.result("pg10").run();
    model.result("pg10").feature("lss1").create("tran1", "Transparency");
    model.result("pg10").run();
    model.result("pg10").feature("lss1").feature("tran1").set("transparency", 0.2);
    model.result("pg10").run();
    model.result("pg10").feature("lss2").set("data", "dset3");
    model.result("pg10").feature("lss2").set("titletype", "none");
    model.result("pg10").feature("lss2").set("wireframe", false);
    model.result("pg10").feature("lss2").set("inheritplot", "lss1");
    model.result().setOnlyPlotWhenRequested(true);
    model.result("pg10").create("line1", "Line");
    model.result("pg10").feature("line1").set("data", "dset1lshelllshl");
    model.result("pg10").feature("line1").set("expr", "1");
    model.result("pg10").feature("line1").set("titletype", "none");
    model.result("pg10").feature("line1").set("linetype", "tube");
    model.result("pg10").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg10").feature("line1").set("tuberadiusscale", 0.001);
    model.result("pg10").feature("line1").set("coloring", "uniform");
    model.result("pg10").feature("line1").set("color", "gray");
    model.result("pg10").feature("line1").set("inheritcolor", false);
    model.result("pg10").feature("line1").set("inheritrange", false);
    model.result("pg10").feature("line1").set("inheritplot", "lss1");
    model.result("pg10").feature("line1").create("def1", "Deform");
    model.result("pg10").feature("line1").feature("def1").set("expr", new String[]{"lshell.umX", "v", "w"});
    model.result("pg10").feature("line1").feature("def1").setIndex("expr", "lshell.umY", 1);
    model.result("pg10").feature("line1").feature("def1").setIndex("expr", "lshell.umZ", 2);
    model.result("pg10").feature("line1").set("data", "parent");
    model.result("pg10").feature().duplicate("line2", "line1");
    model.result("pg10").feature("line2").set("data", "dset2");
    model.result("pg10").feature("line2").set("color", "black");
    model.result().setOnlyPlotWhenRequested(false);
    model.result("pg10").run();
    model.result("pg10").label("\u4f4d\u79fb\uff1a\u539f\u59cb\u548c\u4f18\u5316");
    model.result("pg10").set("view", "auto");
    model.result("pg10").run();
    model.result().evaluationGroup().duplicate("eg3", "eg1");
    model.result().evaluationGroup("eg3")
         .label("\u6700\u5927\u5931\u6548\u6307\u6570\uff08\u4f18\u5316\u503c\uff09");
    model.result().evaluationGroup("eg3").set("data", "dset2");
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup().duplicate("eg4", "eg2");
    model.result().evaluationGroup("eg4")
         .label("\u4e0d\u540c\u5931\u6548\u6a21\u5f0f\u7684\u5931\u6548\u6307\u6570\uff08\u4f18\u5316\u503c\uff09");
    model.result().evaluationGroup("eg4").set("data", "dset3lshelllshl");
    model.result().evaluationGroup("eg4").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg4").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").add("evaluationgroup", "eg1");
    model.nodeGroup("grp1").add("evaluationgroup", "eg2");
    model.nodeGroup("grp1").label("\u539f\u59cb\u94fa\u5c42");

    model.result("pg6").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").add("plotgroup", "pg9");
    model.nodeGroup("grp2").add("plotgroup", "pg10");
    model.nodeGroup("grp2").add("evaluationgroup", "eg3");
    model.nodeGroup("grp2").add("evaluationgroup", "eg4");
    model.nodeGroup("grp2").label("\u4f18\u5316\u540e\u7684\u94fa\u5c42");

    model.result("pg10").run();

    model.title("\u94fa\u5c42\u987a\u5e8f\u7684\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u57fa\u4e8e Hashin \u5931\u6548\u51c6\u5219\u4f18\u5316\u5305\u542b\u516d\u5c42\u5bf9\u79f0\u89d2\u94fa\u8bbe\u5c42\u7684\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u4e2d\u7684\u94fa\u5c42\u987a\u5e8f\uff0c\u5176\u4e2d\u4f7f\u7528\u5177\u6709\u6a2a\u5411\u5404\u5411\u540c\u6027\u6750\u6599\u5c5e\u6027\u7684\u78b3-\u73af\u6c27\u6811\u8102\u6750\u6599\u4f5c\u4e3a\u8584\u5c42\u6750\u6599\uff0c\u901a\u8fc7\u6267\u884c\u4f18\u5316\u5206\u6790\u6765\u786e\u5b9a\u6bcf\u5c42\u7684\u6700\u4f73\u7ea4\u7ef4\u65b9\u5411\uff0c\u4ee5\u786e\u4fdd\u5728\u7ed9\u5b9a\u7684\u8f7d\u8377\u6761\u4ef6\u4e0b\uff0c\u5c42\u5408\u677f\u80fd\u591f\u4fdd\u6301\u5b89\u5168\u548c\u9002\u7528\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("stacking_sequence_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
