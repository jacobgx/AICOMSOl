/*
 * dense_suspension.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class dense_suspension {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics().create("phtr", "PhaseTransportFree", "geom1");
    model.component("comp1").physics("phtr").field("volumefraction").field("phic");
    model.component("comp1").physics("phtr").field("volumefraction").component(new String[]{"phic", "phid"});

    model.component("comp1").multiphysics().create("mfmm1", "MultiphaseFlowMixtureModel", 2);
    model.component("comp1").multiphysics("mfmm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfmm1").set("fluidflow_physics", "spf");
    model.component("comp1").multiphysics("mfmm1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mfmm1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho_s", "1180[kg/m^3]", "\u56fa\u4f53\u9897\u7c92\u5bc6\u5ea6");
    model.param().set("rho_f", "1253[kg/m^3]", "\u7eaf\u6d41\u4f53\u7684\u5bc6\u5ea6");
    model.param().set("eta_f", "0.5889[Pa*s]", "\u7eaf\u6d41\u4f53\u7684\u9ecf\u5ea6");
    model.param().set("phi_max", "0.64", "\u6700\u5927\u586b\u5145\u7387");
    model.param().set("phi0", "0.35", "\u5e73\u5747\u6d53\u5ea6");
    model.param().set("R", "0.397[mm]", "\u9897\u7c92\u534a\u5f84");
    model.param().set("RPM", "55[1/min]", "\u6bcf\u5206\u949f\u8f6c\u6570");
    model.param().set("c_vel", "2*pi*RPM", "\u901f\u5ea6\u6bd4\u4f8b");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.0064);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 0.0254);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(1);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(3, 4, 6, 7);
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "Manual");
    model.component("comp1").physics("spf").feature("wallbc2").set("utr", new String[]{"c_vel*y", "-c_vel*x", "0"});
    model.component("comp1").physics("phtr").feature("init1").setIndex("s0", "phi0", 1);

    model.component("comp1").multiphysics("mfmm1").set("SlipModel", "HadamardRybczynski");
    model.component("comp1").multiphysics("mfmm1").set("phimax", "phi_max");
    model.component("comp1").multiphysics("mfmm1").set("ShearInducedMigration", true);
    model.component("comp1").multiphysics("mfmm1").set("rhoc_mat", "userdef");
    model.component("comp1").multiphysics("mfmm1").set("rhoc", "rho_f");
    model.component("comp1").multiphysics("mfmm1").set("muc_mat", "userdef");
    model.component("comp1").multiphysics("mfmm1").set("muc", "eta_f");
    model.component("comp1").multiphysics("mfmm1").set("rho_phid_mat", "userdef");
    model.component("comp1").multiphysics("mfmm1").set("rho_phid", "rho_s");
    model.component("comp1").multiphysics("mfmm1").set("diam_phid", "2*R");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.0012);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "0 30 100 1000");
    model.study("std1").showAutoSequences("all");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u4f53\u79ef\u5206\u6570 (phtr)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "phic");
    model.result("pg3").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "phid");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", 0.21);
    model.result("pg3").feature("surf1").set("rangecolormax", 0.45);
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").run();

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("smooth", "2*2");

    model.component("comp1").physics("phtr").create("init2", "InitialValues", 2);
    model.component("comp1").physics("phtr").feature("init2").selection().all();
    model.component("comp1").physics("phtr").feature("init2").setIndex("s0", "step1(y[1/mm]-8)*0.59", 1);

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh2").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", "0.0006");
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/phtr", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/mfmm1", true);
    model.study("std2").feature("time").set("tlist", "range(0,10,100)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u901f\u5ea6 (spf) 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u538b\u529b (spf) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("expr", "p");
    model.result("pg5").feature("con1").set("number", 40);
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("smooth", "internal");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u4f53\u79ef\u5206\u6570 (phtr) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "phic");
    model.result("pg6").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg6").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg4").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "phid");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormin", 0);
    model.result("pg6").feature("surf1").set("rangecolormax", 0.62);
    model.result("pg6").run();
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("color", "black");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 2, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").run();

    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"phtr/init2"});

    model.result("pg6").run();

    model.title("\u6d53\u60ac\u6d6e\u6db2\u7684\u4e24\u76f8\u6d41\u5efa\u6a21");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e24\u4e2a\u540c\u5fc3\u5706\u67f1\u5bb9\u5668\u4e2d\u60ac\u6d6e\u6d41\u4f53\u7684\u6d41\u52a8\u3002\u5185\u90e8\u7684\u5706\u67f1\u5bb9\u5668\u65cb\u8f6c\uff0c\u5916\u90e8\u7684\u5706\u67f1\u5bb9\u5668\u56fa\u5b9a\u3002\u60ac\u6d6e\u6d41\u4f53\u6d53\u5ea6\u9ad8\uff0c\u5373\u56fa\u4f53\u9897\u7c92\u7684\u4f53\u79ef\u5206\u6570\u5f88\u9ad8\u3002\u8be5\u6a21\u578b\u5206\u6790\u4e86\u6d6e\u529b\u6548\u5e94\u548c\u526a\u5207\u8bf1\u5bfc\u5f15\u8d77\u7684\u8fc1\u79fb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("dense_suspension.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
