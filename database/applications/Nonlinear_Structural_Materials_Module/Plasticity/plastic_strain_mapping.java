/*
 * plastic_strain_mapping.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:35 by COMSOL 6.3.0.290. */
public class plastic_strain_mapping {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{18, 10});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 5);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("dif1");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").set("youngsmodulus", new String[]{"70e9"});
    model.material("mat1").propertyGroup("def").set("poissonsratio", new String[]{"0.2"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"7850"});
    model.material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", new String[]{"243e6"});
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Et", new String[]{"2.171e9"});

    model.param().set("para", "0");
    model.param().descr("para", "\u6c34\u5e73\u8f7d\u8377\u53c2\u6570");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "loadfunc");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 0, 0, 1);
    model.func("int1").setIndex("table", 1.1, 1, 0);
    model.func("int1").setIndex("table", 133.65, 1, 1);
    model.func("int1").setIndex("table", 2.2, 2, 0);
    model.func("int1").setIndex("table", 0, 2, 1);
    model.func("int1").setIndex("argunit", 1, 0);
    model.func("int1").setIndex("fununit", "MPa", 0);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", "10[mm]");
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 3);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(4);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"loadfunc(para)", "0", "0"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").create("ref1", "Refine");
    model.component("comp1").mesh("mesh1").feature("ref1").set("boxcoord", true);
    model.component("comp1").mesh("mesh1").feature("ref1").set("xmax", 8);
    model.component("comp1").mesh("mesh1").feature("ref1").set("ymax", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 range(0.40,0.05,2.2)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 38, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff0c\u4e09\u89d2\u5f62\u7f51\u683c");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("elemcolor", "none");
    model.result("pg1").feature("mesh1").create("def1", "Deform");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 38, 0);
    model.result("pg2").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg2").feature("surf1").set("inheritplot", "none");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("bandcount", 11);
    model.result("pg2").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg2").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg2").run();
    model.result("pg2").label("\u5851\u6027\u533a\u57df\uff0c\u4e09\u89d2\u5f62\u7f51\u683c");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid.epeGp>0");
    model.result("pg2").feature("surf1").set("descr", "solid.epeGp>0");
    model.result("pg2").run();
    model.result("pg2").create("mesh1", "Mesh");
    model.result("pg2").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg2").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg2").feature("mesh1").set("elemcolor", "none");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("type", "sequence");
    model.component("comp2").geom("geom2").feature("imp1").set("sequence", "geom1");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().create("matlnk2", "Link");

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/solid2", true);

    model.component("comp2").physics("solid2").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp2").physics("solid2").prop("d").set("d", "10[mm]");
    model.component("comp2").physics("solid2").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp2").physics("solid2").feature("lemm1").feature("plsty1").create("setv1", "SetVariables", 2);

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"X", "Y"});
    model.component("comp1").cpl("genext1").set("srcframe", "material");
    model.component("comp1").cpl("genext1").selection().set(1);

    model.component("comp2").physics("solid2").feature("lemm1").feature("plsty1").feature("setv1")
         .set("SettingCondition", "para<0.8001");
    model.component("comp2").physics("solid2").feature("lemm1").feature("plsty1").feature("setv1")
         .set("epeSet", "comp1.genext1(solid.epe)");
    model.component("comp2").physics("solid2").feature("lemm1").feature("plsty1").feature("setv1")
         .set("epSet", new String[]{"comp1.genext1(solid.epXX)", "comp1.genext1(solid.epXY)", "0", "comp1.genext1(solid.epXY)", "comp1.genext1(solid.epYY)", "0", "0", "0", "comp1.genext1(solid.epZZ)"});
    model.component("comp2").physics("solid2").create("sym1", "SymmetrySolid", 1);
    model.component("comp2").physics("solid2").feature("sym1").selection().set(1, 3);
    model.component("comp2").physics("solid2").create("bndl1", "BoundaryLoad", 1);
    model.component("comp2").physics("solid2").feature("bndl1").selection().set(4);
    model.component("comp2").physics("solid2").feature("bndl1")
         .set("forceReferenceArea", new String[]{"loadfunc(para)", "0", "0"});

    model.component("comp2").mesh("mesh2").create("fq1", "FreeQuad");
    model.component("comp2").mesh("mesh2").feature("size").set("hauto", 3);
    model.component("comp2").mesh("mesh2").feature("fq1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("fq1").feature("size1").selection().geom("geom2", 1);
    model.component("comp2").mesh("mesh2").feature("fq1").feature("size1").selection().set(5);
    model.component("comp2").mesh("mesh2").feature("fq1").feature("size1").set("hauto", 2);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("solnum", 10);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").feature("stat").set("notsolnum", 10);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0.80,0.05,2.2)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 29, 0);
    model.result("pg3").label("\u5e94\u529b (solid2)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff0c\u56db\u8fb9\u5f62\u7f51\u683c");
    model.result("pg3").set("edges", false);
    model.result("pg3").create("mesh1", "Mesh");
    model.result("pg3").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg3").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg3").feature("mesh1").set("elemcolor", "none");
    model.result("pg3").feature("mesh1").create("def1", "Deform");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 29, 0);
    model.result("pg4").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid2)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid2.epeGp"});
    model.result("pg4").feature("surf1").set("inheritplot", "none");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortabletype", "discrete");
    model.result("pg4").feature("surf1").set("bandcount", 11);
    model.result("pg4").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg4").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid2)");
    model.result("pg4").run();
    model.result("pg4").label("\u5851\u6027\u533a\u57df\uff0c\u56db\u8fb9\u5f62\u7f51\u683c");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "solid2.epeGp>0");
    model.result("pg4").feature("surf1").set("descr", "solid2.epeGp>0");
    model.result("pg4").run();
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").set("elemcolor", "none");

    model.title("\u5851\u6027\u5e94\u53d8\u6620\u5c04");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u53d7\u8f7d\u8377\u51fa\u73b0\u5851\u6027\u7684\u7a7f\u5b54\u677f\u8fdb\u884c\u5206\u6790\uff0c\u76ee\u7684\u662f\u8bf4\u660e\u5982\u4f55\u5728\u4e0d\u540c\u7684\u7f51\u683c\u4e4b\u95f4\u6620\u5c04\u5851\u6027\u5e94\u53d8\u3002");

    model.label("plastic_strain_mapping.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
