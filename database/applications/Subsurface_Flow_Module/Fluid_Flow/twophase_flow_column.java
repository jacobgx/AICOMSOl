/*
 * twophase_flow_column.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:30 by COMSOL 6.3.0.290. */
public class twophase_flow_column {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").field("pressure").field("p_w");
    model.component("comp1").physics().create("dl2", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl2").field("pressure").field("p_nw");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/physics/dl2", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho_water", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6c34");
    model.param().set("mu_water", "1e-3[Pa*s]", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u6c34");
    model.param().set("rho_air", "1.28[kg/m^3]", "\u5bc6\u5ea6\uff0c\u7a7a\u6c14");
    model.param().set("mu_air", "1.81e-5[Pa*s]", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u7a7a\u6c14");
    model.param().set("rho_oil", "800[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6cb9");
    model.param().set("mu_oil", "3.92e-3[Pa*s]", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u6cb9");
    model.param().set("sigma_aw", "0.0681[H/m]", "\u754c\u9762\u5f20\u529b\uff0c\u7a7a\u6c14-\u6c34");
    model.param().set("sigma_ow", "0.0364[H/m]", "\u754c\u9762\u5f20\u529b\uff0c\u6cb9-\u6c34");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "twophase_flow_column_interpolation.txt");
    model.func("int1").importData();
    model.func("int1").setIndex("fununit", "m", 0);
    model.func("int1").setIndex("argunit", "h", 0);
    model.func("int1").set("funcname", "Hp_nw_t");
    model.func("int1").label("\u5165\u53e3\u7a7a\u6c14\u538b\u5934");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.06, 0.0834});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.0074, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("smoothzonelocactive", true);
    model.component("comp1").func("rm1").set("smoothzoneloc", "1e-3");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").label("\u7a7a\u6c14-\u6c34\u5b9e\u9a8c");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("rho_w", "rho_water", "\u5bc6\u5ea6\uff0c\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var1")
         .set("mu_w", "mu_water", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var1")
         .set("rho_nw", "rho_air", "\u5bc6\u5ea6\uff0c\u975e\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var1")
         .set("mu_nw", "mu_air", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u975e\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var1").set("alpha", "1.89[1/m]", "Van Genuchten \u03b1 \u53c2\u6570");
    model.component("comp1").variable("var1").set("L", "0.5", "Van Genuchten L \u53c2\u6570");
    model.component("comp1").variable("var1")
         .set("N", "2.811", "Van Genuchten N \u53c2\u6570\uff0c\u7a7a\u6c14-\u6c34");
    model.component("comp1").variable("var1").set("M", "1-1/N", "Van Genuchten M \u53c2\u6570");
    model.component("comp1").variable("var1").set("kap_su", "2480[mD]", "\u6e17\u900f\u7387\uff0c\u4e0a\u5c42");
    model.component("comp1").variable("var1")
         .set("theta_ru", "0.021", "\u6b8b\u4f59\u4f53\u79ef\u5206\u6570\uff0c\u4e0a\u5c42");
    model.component("comp1").variable("var1")
         .set("sigma", "1", "\u754c\u9762\u5f20\u529b\u4e4b\u95f4\u7684\u6bd4\u7387");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("Van Genuchten \u6301\u6c34\u6a21\u578b");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2").set("p_c", "p_nw-p_w", "\u6bdb\u7ec6\u538b\u529b");
    model.component("comp1").variable("var2")
         .set("Hc", "p_c/(rho_w*g_const)", "\u6bdb\u7ec6\u538b\u529b\u6c34\u5934");
    model.component("comp1").variable("var2")
         .set("Se_w", "(1+(alpha*rm1(Hc[1/m])[m])^N)^(-M)", "\u6709\u6548\u9971\u548c\u5ea6\uff0c\u6e7f\u76f8");
    model.component("comp1").variable("var2")
         .set("Se_nw", "1-Se_w", "\u6709\u6548\u9971\u548c\u5ea6\uff0c\u975e\u6e7f\u76f8");
    model.component("comp1").variable("var2")
         .set("theta_w", "theta_r+Se_w*(theta_s-theta_r)", "\u4f53\u79ef\u5206\u6570\uff0c\u6e7f\u76f8");
    model.component("comp1").variable("var2")
         .set("theta_nw", "theta_s-theta_w", "\u4f53\u79ef\u5206\u6570\uff0c\u975e\u6e7f\u76f8");
    model.component("comp1").variable("var2")
         .set("kr_w", "(Se_w^L*(1-(1-Se_w^(1/M))^M)^2)+eps", "\u76f8\u5bf9\u6e17\u900f\u7387\uff0c\u6e7f\u76f8");
    model.component("comp1").variable("var2")
         .set("kr_nw", "((1-Se_w)^L*(1-Se_w^(1/M))^(2*M))+eps", "\u76f8\u5bf9\u6e17\u900f\u7387\uff0c\u975e\u6e7f\u76f8");
    model.component("comp1").variable("var2")
         .set("Cp", "((alpha*M/(1-M)*(theta_s-theta_r)*Se_w^(1/M)*(1-Se_w^(1/M))^M))/(rho_w*g_const)", "\u6bd4\u5bb9\u91cf");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u521d\u59cb\u548c\u8fb9\u754c\u6761\u4ef6");

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3").set("p_nw_top", "Hp_nw_t(t)*rho_w*g_const*sigma");
    model.component("comp1").variable("var3").set("p_nw_init", "p_nw_top+(8.34[cm]-y)*rho_nw*g_const");
    model.component("comp1").variable("var3").set("p_w_init", "-rho_w*g_const*y");
    model.component("comp1").variable("var3").set("p_w0", "0.1[m]*sigma*rho_w*g_const*(t/170[h])");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("\u4e0b\u5c42");
    model.component("comp1").variable("var4").selection().geom("geom1", 2);
    model.component("comp1").variable("var4").selection().set(1);

//    To import content from file, use:
//    model.component("comp1").variable("var4").loadFile("FILENAME");
    model.component("comp1").variable("var4").set("kap_s", "13.57[mD]", "\u6e17\u900f\u7387\uff0c\u4e0b\u5c42");
    model.component("comp1").variable("var4")
         .set("theta_s", "0.5", "\u9971\u548c\u4f53\u79ef\u5206\u6570\uff0c\u4e0b\u5c42");
    model.component("comp1").variable("var4")
         .set("theta_r", "0", "\u6b8b\u4f59\u4f53\u79ef\u5206\u6570\uff0c\u4e0b\u5c42");
    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").label("\u4e0a\u5c42");
    model.component("comp1").variable("var5").selection().geom("geom1", 2);
    model.component("comp1").variable("var5").selection().set(2);

//    To import content from file, use:
//    model.component("comp1").variable("var5").loadFile("FILENAME");
    model.component("comp1").variable("var5").set("kap_s", "kap_su", "\u6e17\u900f\u7387\uff0c\u4e0a\u5c42");
    model.component("comp1").variable("var5")
         .set("theta_s", "0.32", "\u9971\u548c\u4f53\u79ef\u5206\u6570\uff0c\u4e0a\u5c42");
    model.component("comp1").variable("var5")
         .set("theta_r", "theta_ru", "\u6b8b\u4f59\u4f53\u79ef\u5206\u6570\uff0c\u4e0a\u5c42");

    model.component("comp1").physics("dl").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho", "rho_w");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu", "mu_w");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", 0.25);
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kap_s", "0", "0", "0", "kap_s", "0", "0", "0", "kap_s"});
    model.component("comp1").physics("dl").feature().duplicate("porous2", "porous1");
    model.component("comp1").physics("dl").feature("porous2").selection().set(2);
    model.component("comp1").physics("dl").feature("porous2").set("storageModelType", "userdef");
    model.component("comp1").physics("dl").feature("porous2").set("Sp", "Cp");
    model.component("comp1").physics("dl").feature("porous2").feature("pm1")
         .set("kappa", new String[]{"kap_s*kr_w", "0", "0", "0", "kap_s*kr_w", "0", "0", "0", "kap_s*kr_w"});
    model.component("comp1").physics("dl").feature("init1").set("p", "p_w_init");
    model.component("comp1").physics("dl").create("pr1", "Pressure", 1);
    model.component("comp1").physics("dl").feature("pr1").selection().set(2);
    model.component("comp1").physics("dl").feature("pr1").set("p0", "p_w0");
    model.component("comp1").physics("dl").create("ms1", "MassSource", 2);
    model.component("comp1").physics("dl").feature("ms1").selection().set(2);
    model.component("comp1").physics("dl").feature("ms1").set("Qm", "Cp*p_nwt*rho_w");
    model.component("comp1").physics("dl2").selection().set(2);
    model.component("comp1").physics("dl2").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("dl2").feature("porous1").set("storageModelType", "userdef");
    model.component("comp1").physics("dl2").feature("porous1").set("Sp", "Cp");
    model.component("comp1").physics("dl2").feature("porous1").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("dl2").feature("porous1").feature("fluid1").set("rho", "rho_nw");
    model.component("comp1").physics("dl2").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("dl2").feature("porous1").feature("fluid1").set("mu", "mu_nw");
    model.component("comp1").physics("dl2").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl2").feature("porous1").feature("pm1").set("epsilon", 0.25);
    model.component("comp1").physics("dl2").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl2").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kap_s*kr_nw", "0", "0", "0", "kap_s*kr_nw", "0", "0", "0", "kap_s*kr_nw"});
    model.component("comp1").physics("dl2").feature("init1").set("p", "p_nw_init");
    model.component("comp1").physics("dl2").create("pr1", "Pressure", 1);
    model.component("comp1").physics("dl2").feature("pr1").selection().set(5);
    model.component("comp1").physics("dl2").feature("pr1").set("p0", "p_nw_top");
    model.component("comp1").physics("dl2").create("ms1", "MassSource", 2);
    model.component("comp1").physics("dl2").feature("ms1").selection().set(2);
    model.component("comp1").physics("dl2").feature("ms1").set("Qm", "Cp*p_wt*rho_nw");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7a7a\u6c14-\u6c34");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "0 0.001 0.01 0.1 range(1,170)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", "0 0 0");
    model.result().dataset("cpt1").set("pointy", "0.075 0.045 0.015");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u8fdb\u6c14\u538b\u529b\u548c\u6bdb\u7ec6\u538b\u529b\uff0c\u7a7a\u6c14-\u6c34");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "time[h]");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u5165\u53e3\u7a7a\u6c14\u538b\u5934\uff08\u5b9e\u7ebf\uff09\u548c\u6bdb\u7ec6\u538b\u5934\uff08\u865a\u7ebf\uff09");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(6);
    model.result("pg1").feature("ptgr1").set("expr", "Hp_nw_t(t)");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg1").feature("ptgr1").setIndex("legends", "\u5165\u53e3\u538b\u5934\uff0c\u4e0a\u8fb9\u754c", 0);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").set("expr", "Hc");
    model.result("pg1").feature("ptgr2").set("descr", "\u6bdb\u7ec6\u538b\u529b\u6c34\u5934");
    model.result("pg1").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg1").feature("ptgr2").set("legendmethod", "automatic");
    model.result("pg1").feature("ptgr2").set("autopoint", false);
    model.result("pg1").feature("ptgr2").set("autodescr", true);
    model.result("pg1").feature("ptgr2").set("legendsuffix", "\uff0c\u4e0a\u8fb9\u754c");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr3").set("data", "cpt1");
    model.result("pg1").feature("ptgr3").set("autopoint", true);
    model.result("pg1").feature("ptgr3").set("autodescr", false);
    model.result("pg1").feature("ptgr3").set("legendsuffix", "");
    model.result("pg1").feature("ptgr3").set("legendprefix", "\u6bdb\u7ec6\u538b\u5934\uff0c");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("3 \u4e2a\u70b9\u5904\u7684\u76f8\u5bf9\u6e17\u900f\u7387");
    model.result("pg2").set("data", "cpt1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "\u4e0d\u540c\u70b9\u5904\u6da6\u6e7f\u6d41\u4f53\uff08\u5b9e\u7ebf\uff09\u548c\u975e\u6da6\u6e7f\u6d41\u4f53\uff08\u865a\u7ebf\uff09\u7684\u76f8\u5bf9\u6e17\u900f\u7387");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u65f6\u95f4 [h]");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").set("expr", "kr_w");
    model.result("pg2").feature("ptgr1").set("descr", "\u76f8\u5bf9\u6e17\u900f\u7387\uff0c\u6e7f\u76f8");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("autoexpr", true);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("expr", "kr_nw");
    model.result("pg2").feature("ptgr2").set("descr", "\u76f8\u5bf9\u6e17\u900f\u7387\uff0c\u975e\u6e7f\u76f8");
    model.result("pg2").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg2").feature("ptgr2").set("linecolor", "cyclereset");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "Se_nw");
    model.result("pg3").feature("surf1").set("descr", "\u6709\u6548\u9971\u548c\u5ea6\uff0c\u975e\u6e7f\u76f8");
    model.result("pg3").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("\u6709\u6548\u9971\u548c\u5ea6\uff0c\u975e\u6da6\u6e7f\u76f8");

    model.component("comp1").variable().create("var6");
    model.component("comp1").variable("var6").label("\u7a7a\u6c14-\u6cb9\u5b9e\u9a8c");

//    To import content from file, use:
//    model.component("comp1").variable("var6").loadFile("FILENAME");
    model.component("comp1").variable("var6").set("rho_w", "rho_oil", "\u5bc6\u5ea6\uff0c\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var6")
         .set("mu_w", "mu_oil", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var6")
         .set("rho_nw", "rho_air", "\u5bc6\u5ea6\uff0c\u975e\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var6")
         .set("mu_nw", "mu_air", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u975e\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var6").set("alpha", "3.58[1/m]", "Van Genuchten \u03b1 \u53c2\u6570");
    model.component("comp1").variable("var6").set("L", "0.5", "Van Genuchten L \u53c2\u6570");
    model.component("comp1").variable("var6").set("N", "3.1365", "Van Genuchten N \u53c2\u6570");
    model.component("comp1").variable("var6").set("M", "1-1/N", "Van Genuchten M \u53c2\u6570");
    model.component("comp1").variable("var6").set("kap_su", "1104.41[mD]", "\u6e17\u900f\u7387\uff0c\u4e0a\u5c42");
    model.component("comp1").variable("var6")
         .set("theta_ru", "0.0072", "\u6b8b\u4f59\u4f53\u79ef\u5206\u6570\uff0c\u4e0a\u5c42");
    model.component("comp1").variable("var6")
         .set("sigma_ao", "0.0259[H/m]", "\u754c\u9762\u5f20\u529b\uff0c\u7a7a\u6c14-\u6cb9");
    model.component("comp1").variable("var6")
         .set("sigma", "sigma_ao/sigma_aw", "\u754c\u9762\u5f20\u529b\u4e4b\u95f4\u7684\u6bd4\u7387");
    model.component("comp1").variable().create("var7");
    model.component("comp1").variable("var7").label("\u6cb9-\u6c34\u5b9e\u9a8c");

//    To import content from file, use:
//    model.component("comp1").variable("var7").loadFile("FILENAME");
    model.component("comp1").variable("var7")
         .set("rho_w", "rho_water", "\u5bc6\u5ea6\uff0c\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var7")
         .set("mu_w", "mu_water", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var7")
         .set("rho_nw", "rho_oil", "\u5bc6\u5ea6\uff0c\u975e\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var7")
         .set("mu_nw", "mu_oil", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u975e\u6da6\u6e7f\u6d41\u4f53");
    model.component("comp1").variable("var7").set("alpha", "3.58[1/m]", "Van Genuchten \u03b1 \u53c2\u6570");
    model.component("comp1").variable("var7").set("L", "0.5", "van Genuchten L \u53c2\u6570");
    model.component("comp1").variable("var7").set("N", "3.1365", "van Genuchten N \u53c2\u6570");
    model.component("comp1").variable("var7").set("M", "1-1/N", "van Genuchten M \u53c2\u6570");
    model.component("comp1").variable("var7").set("kap_su", "952.45[mD]", "\u6e17\u900f\u7387\uff0c\u4e0a\u5c42");
    model.component("comp1").variable("var7")
         .set("theta_ru", "0.0072", "\u6b8b\u4f59\u4f53\u79ef\u5206\u6570\uff0c\u4e0a\u5c42");
    model.component("comp1").variable("var7").set("sigma_ow", "0.0364[H/m]", "\u754c\u9762\u5f20\u529b");
    model.component("comp1").variable("var7")
         .set("sigma", "sigma_ow/sigma_aw", "\u754c\u9762\u5f20\u529b\u4e4b\u95f4\u7684\u6bd4\u7387");

    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledvariables", new String[]{"var6", "var7"});
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/dl", true);
    model.study("std2").feature("time").setSolveFor("/physics/dl2", true);
    model.study("std2").label("\u7a7a\u6c14-\u6cb9");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("time").set("tunit", "h");
    model.study("std2").feature("time").set("tlist", "0 0.001 0.01 0.1 range(1,170)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledvariables", new String[]{"var1", "var7"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").set("data", "dset2");
    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");
    model.result("pg4").run();
    model.result("pg4")
         .label("\u5165\u53e3\u7a7a\u6c14\u538b\u529b\u548c\u6bdb\u7ec6\u538b\u529b\uff0c\u7a7a\u6c14-\u6cb9");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("expr", "Hp_nw_t(t)*sigma");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr3").set("data", "cpt2");
    model.result("pg4").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/dl", true);
    model.study("std3").feature("time").setSolveFor("/physics/dl2", true);
    model.study("std3").label("\u6cb9-\u6c34");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("time").set("tunit", "h");
    model.study("std3").feature("time").set("tlist", "0 0.001 0.01 0.1 range(1,170)");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledvariables", new String[]{"var1", "var6"});
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().duplicate("cpt3", "cpt2");
    model.result().dataset("cpt3").set("data", "dset3");
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5")
         .label("\u5165\u53e3\u7a7a\u6c14\u538b\u529b\u548c\u6bdb\u7ec6\u538b\u529b\uff0c\u6cb9-\u6c34");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr3").set("data", "cpt3");
    model.result("pg5").run();

    model.title("\u67f1\u4e2d\u4e24\u76f8\u6d41");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5728\u591a\u7ea7\u5165\u53e3\u538b\u529b\u5b9e\u9a8c\u4e2d\uff0c\u4e24\u79cd\u4e0d\u6df7\u6eb6\u6d41\u4f53\u5728\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u540c\u65f6\u6d41\u52a8\u3002\u6a21\u578b\u6c42\u89e3\u4e00\u4e2a\u4ee3\u8868\u6027\u4f53\u5185\u7a7a\u6c14\u548c\u6c34\u7684\u538b\u529b\u548c\u9971\u548c\u5ea6\uff0c\u56e0\u6b64\u8ddf\u8e2a\u9971\u548c\u5ea6\u6c34\u5e73\uff0c\u800c\u4e0d\u662f\u4f30\u8ba1\u7a7a\u6c14-\u6c34\u754c\u9762\u7684\u79bb\u6563\u4f4d\u7f6e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("twophase_flow_column.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
