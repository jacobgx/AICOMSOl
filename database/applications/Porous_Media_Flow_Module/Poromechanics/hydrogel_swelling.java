/*
 * hydrogel_swelling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:14 by COMSOL 6.3.0.290. */
public class hydrogel_swelling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Poromechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").feature("porous1").set("storageModelType", new String[]{"poroelastic"});

    model.component("comp1").multiphysics().create("poro1", "PoroelasticCoupling", 2);
    model.component("comp1").multiphysics("poro1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("poro1").set("PorousMediaFlow_physics", "dl");
    model.component("comp1").multiphysics("poro1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/poro1", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().set("height", "1[mm]");
    model.param().descr("height", "\u51dd\u80f6\u9ad8\u5ea6");
    model.param().set("ar", "0.2");
    model.param().descr("ar", "\u51dd\u80f6\u7eb5\u6a2a\u6bd4");
    model.param().set("radius", "ar*height/2");
    model.param().descr("radius", "\u51dd\u80f6\u534a\u5f84");
    model.param().set("elsize_sw", "if(ar<=2,0.05*height,0.10*height)");
    model.param().descr("elsize_sw", "\u5355\u5143\u5927\u5c0f\uff0c\u81a8\u80c0\u8f7d\u8377\u5de5\u51b5");
    model.param().set("elsize_c", "0.01*height");
    model.param().descr("elsize_c", "\u5355\u5143\u5927\u5c0f\uff0c\u56fa\u7ed3\u8f7d\u8377\u5de5\u51b5");
    model.param().create("par2");
    model.param("par2").label("\u6750\u6599\u53c2\u6570");
    model.param("par2").set("G", "4e4[Pa]");
    model.param("par2").descr("G", "\u521d\u59cb\u526a\u5207\u6a21\u91cf");
    model.param("par2").set("Kmix", "4e7[Pa]");
    model.param("par2").descr("Kmix", "\u6df7\u5408\u7684\u4f53\u79ef\u6a21\u91cf");
    model.param("par2").set("chi", "0.1");
    model.param("par2").descr("chi", "\u6df7\u5408\u7113");
    model.param("par2").set("temp", "293.15[K]");
    model.param("par2").descr("temp", "\u53c2\u8003\u6e29\u5ea6");
    model.param("par2").set("Vm", "1e-28[m^3]*N_A_const");
    model.param("par2").descr("Vm", "\u6eb6\u5242\u6469\u5c14\u4f53\u79ef");
    model.param("par2").set("D", "1e3[m^2/s]");
    model.param("par2").descr("D", "\u6eb6\u5242\u6269\u6563\u7cfb\u6570");
    model.param("par2").set("M0", "Vm*D/(N_A_const*k_B_const*temp)");
    model.param("par2").descr("M0", "\u6eb6\u5242\u8fc1\u79fb\u7387");
    model.param("par2").set("rhof", "1[g/cm^3]");
    model.param("par2").descr("rhof", "\u6eb6\u5242\u5bc6\u5ea6");
    model.param("par2").set("muf", "1e-3[mPa*s]");
    model.param("par2").descr("muf", "\u6eb6\u5242\u9ecf\u5ea6");
    model.param("par2").set("kappa0", "M0*muf");
    model.param("par2").descr("kappa0", "\u6e17\u900f\u7387");
    model.param().create("par3");
    model.param("par3").label("\u6a21\u578b\u53c2\u6570");
    model.param("par3").set("stretch0", "2");
    model.param("par3").descr("stretch0", "\u81ea\u7531\u81a8\u80c0\u62c9\u4f38");
    model.param("par3").set("J0", "stretch0^3");
    model.param("par3").descr("J0", "\u81ea\u7531\u81a8\u80c0\u4f53\u79ef\u6bd4");
    model.param("par3").set("mu_ref", "G/J0*(stretch0^2-1)+Kmix*(log(1-1/J0)+1/J0+chi/(J0^2))");
    model.param("par3").descr("mu_ref", "\u53c2\u8003\u5316\u5b66\u52bf");
    model.param("par3").set("mu_sw", "0[Pa]");
    model.param("par3").descr("mu_sw", "\u7eaf\u6eb6\u5242\u7684\u5316\u5b66\u52bf");
    model.param("par3").set("poro0", "1-1/J0");
    model.param("par3").descr("poro0", "\u521d\u59cb\u5b54\u9699\u7387");
    model.param("par3").set("t_end", "1000[s]");
    model.param("par3").descr("t_end", "\u6301\u7eed\u65f6\u95f4");

    model.func().create("int1", "Interpolation");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", "mu_ref", 0, 1);
    model.func("int1").setIndex("table", "t_end", 1, 0);
    model.func("int1").setIndex("table", "mu_sw", 1, 1);
    model.func("int1").setIndex("fununit", "Pa", 0);
    model.func("int1").setIndex("argunit", "s", 0);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"radius", "height"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"1.4*radius", "0.1*height"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-0.1*height"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("r2", 3);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", "1/1.4", 0);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").sorder("quadratic");

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().set(4);
    model.component("comp1").pair("p1").destination().set(9);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(2);

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("J", "solid.J*J0", "\u81a8\u80c0\u4f53\u79ef\u6bd4");
    model.component("comp1").variable("var1")
         .set("Psi_s", "G/(2*J0)*(stretch0^2*solid.I1Cel-3-2*log(J0*solid.Jel))", "\u62c9\u4f38\u81ea\u7531\u80fd");
    model.component("comp1").variable("var1")
         .set("Psi_mix", "-Kmix*((solid.Jel-J0^(-1))*log(J0*solid.Jel/(J0*solid.Jel-1))+chi/(J0^2*solid.Jel))", "\u6df7\u5408\u81ea\u7531\u80fd");
    model.component("comp1").variable("var1")
         .set("Kdef", "(solid.J-J0^(-1))*kappa0", "\u53d8\u5f62\u76f8\u5173\u7684\u6e17\u900f\u7387");
    model.component("comp1").variable("var1")
         .set("KRR", "Kdef*solid.Cil11", "\u6e17\u900f\u7387\uff0cRR \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("KRZ", "Kdef*solid.Cil13", "\u6e17\u900f\u7387\uff0cRZ \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("KZZ", "Kdef*solid.Cil33", "\u6e17\u900f\u7387\uff0cZZ \u5206\u91cf");

    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 2);
    model.component("comp1").physics("solid").create("rd1", "RigidDomain", 2);
    model.component("comp1").physics("solid").feature("rd1").selection().set(1);
    model.component("comp1").physics("solid").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm1").selection().set(2);
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "userDefined");
    model.component("comp1").physics("solid").feature("hmm1").set("Ws", "Psi_s+Psi_mix");
    model.component("comp1").physics("solid").feature("hmm1").set("Eequ", "3*G");
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlPenalty", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("fp_penalty", 10);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(7);
    model.component("comp1").physics("dl").selection().set(2);
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", 1);
    model.component("comp1").physics("dl").field("pressure").field("mu");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("fluidType", "incompressible");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rhoref_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rhoref", "rhof");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu", "muf");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", "poro0");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"KRR", "0", "KRZ", "0", "KRR", "0", "KRZ", "0", "KZZ"});
    model.component("comp1").physics("dl").feature("init1").set("p", "mu_ref");
    model.component("comp1").physics("dl").feature("dcont1").set("pairDisconnect", true);
    model.component("comp1").physics("dl").create("pr1", "Pressure", 1);
    model.component("comp1").physics("dl").feature("pr1").label("\u63a5\u89e6\uff08\u81a8\u80c0\uff09");
    model.component("comp1").physics("dl").feature("pr1").selection().set(9);
    model.component("comp1").physics("dl").feature("pr1").set("p0", "if(incontact_p1,mu,int1(t))");
    model.component("comp1").physics("dl").create("pr2", "Pressure", 1);
    model.component("comp1").physics("dl").feature("pr2").label("\u81ea\u7531\u6d41\u52a8\uff08\u81a8\u80c0\uff09");
    model.component("comp1").physics("dl").feature("pr2").set("p0", "int1(t)");
    model.component("comp1").physics("dl").feature("pr2").selection().set(8);

    model.component("comp1").multiphysics("poro1").set("poroelasticityModel", "biphasic");
    model.component("comp1").multiphysics("poro1").set("pfref_src", "root.comp1.dl.pref");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(7, 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1")
         .set("numelem", "floor(radius/elsize_sw)+1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(6, 9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2")
         .set("numelem", "floor(height/elsize_sw)+1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1")
         .set("numelem", "floor(1.4*radius/elsize_sw)+1");
    model.component("comp1").mesh("mesh1").feature("map2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").selection().set(1, 5);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis2").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u975e\u5747\u8d28\u81a8\u80c0");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "ar", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "ar", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.1 0.6 1.0 2.0 6.0 10.0 20.0 100.0", 0);
    model.study("std1").feature("time").set("tlist", "range(0,0.1,1)*t_end");
    model.study("std1").showAutoSequences("sol");

    model.sol("sol1").feature("v1").feature("comp1_mu").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_mu").set("scaleval", "1e5");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "height");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", false);
    model.sol("sol1").feature("t1").set("reacf", false);
    model.sol("sol1").feature("t1").set("storeudot", false);
    model.sol("sol1").feature("t1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 10);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").setIndex("looplevel", 8, 1);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").run();
    model.result("pg1").label("\u81a8\u80c0\u6bd4");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u8868\u9762\uff1a\u81a8\u80c0\u4f53\u79ef\u6bd4 (1)");
    model.result("pg1").set("view", "new");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "comp1.J");
    model.result("pg1").feature("surf1").set("data", "dset2");
    model.result("pg1").feature("surf1").setIndex("looplevel", 2, 1);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletype", "discrete");
    model.result("pg1").run();
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("data", "dset2");
    model.result("pg1").feature("mesh1").setIndex("looplevel", 2, 1);
    model.result("pg1").feature("mesh1").set("elemcolor", "none");
    model.result("pg1").feature("mesh1").set("inheritplot", "surf1");
    model.result("pg1").feature("mesh1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature().duplicate("mesh2", "mesh1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").setIndex("looplevel", 4, 1);
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("trn1").set("move", new double[]{1.25, 0});
    model.result("pg1").run();
    model.result("pg1").feature("mesh2").setIndex("looplevel", 4, 1);
    model.result("pg1").feature("mesh2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("mesh2").feature("trn1").set("move", new double[]{1.25, 0});
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").feature().duplicate("mesh3", "mesh2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").setIndex("looplevel", 6, 1);
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("trn1").set("move", new double[]{3.5, 0});
    model.result("pg1").run();
    model.result("pg1").feature("mesh3").setIndex("looplevel", 6, 1);
    model.result("pg1").run();
    model.result("pg1").feature("mesh3").feature("trn1").set("move", new double[]{3.5, 0});
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.2, 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -0.35, 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "D/H = 0.6", 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "2.0", 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -0.35, 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "D/H = 2", 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "6.0", 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", -0.35, 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "D/H = 10", 2, 2);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").feature("tlan1").set("anchorpoint", "center");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u51dd\u80f6\u9ad8\u5ea6");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u5e73\u8861\u51dd\u80f6\u9ad8\u5ea6");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u51dd\u80f6\u7eb5\u6a2a\u6bd4 (1)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5f52\u4e00\u5316\u51dd\u80f6\u9ad8\u5ea6 h/H<sub>0</sub>");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0.1);
    model.result("pg2").set("xmax", 100);
    model.result("pg2").set("ymin", 3.2);
    model.result("pg2").set("ymax", 5);
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("manualgrid", true);
    model.result("pg2").set("yspacing", 0.2);
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(7);
    model.result("pg2").feature("ptgr1").set("expr", "(height+w)/(height/stretch0)");
    model.result("pg2").feature("ptgr1").set("xdatasolnumtype", "level2");
    model.result("pg2").feature("ptgr1").set("linewidth", 2);
    model.result("pg2").feature("ptgr1").set("linemarker", "circle");

    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "lam0_free", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "G/(lam0_free^3)*(lam0_free^2-1)+Kmix*(log(1-lam0_free^(-3))+lam0_free^(-3)+chi*lam0_free^(-6))-mu_sw", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 2, 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "\u81ea\u7531\u81a8\u80c0\u6bd4", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "lam0_ua", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 1, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "G/(stretch0^2*lam0_ua)*(lam0_ua^2-1)+Kmix*(log(1-1/(stretch0^2*lam0_ua))+1/(stretch0^2*lam0_ua)+chi/(stretch0^4*lam0_ua^2))-mu_sw", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 2, 1, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "\u5355\u8f74\u81a8\u80c0\u6bd4", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomSourceTermUnit", "Pa", 0, 0);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ge", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/poro1", true);
    model.study("std2").label("\u5747\u8d28\u81a8\u80c0");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledpair", new String[]{"p1"});
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").setSolveFor("/physics/dl", false);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid", "dl"});
    model.study("std2").feature("stat").setSolveFor("/multiphysics/poro1", false);
    model.study("std2").feature("stat").set("disabledcoupling", new String[]{"poro1"});
    model.study("std2").feature("stat").setEntry("mesh", "geom1", "nomesh");
    model.study("std2").createAutoSequences("all");

    model.sol("sol11").runAll();

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u5747\u8d28\u81a8\u80c0\u4f38\u957f\u7387");
    model.result().evaluationGroup("eg1").set("data", "dset3");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").run();
    model.result("pg2").run();
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "3.3900", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u81ea\u7531\u5747\u8d28\u81a8\u80c0", 0);
    model.result("pg2").feature("glob1").setIndex("expr", 4.7815, 1);
    model.result("pg2").feature("glob1").setIndex("descr", "\u5355\u8f74\u5747\u8d28\u81a8\u80c0", 1);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg2").create("tlan1", "TableAnnotation");
    model.result("pg2").feature("tlan1").set("source", "localtable");
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 3.2, 0, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 3.45, 0, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u81ea\u7531\u81a8\u80c0", 0, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 3.2, 1, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 4.85, 1, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u5355\u8f74\u81a8\u80c0", 1, 2);
    model.result("pg2").feature("tlan1").set("showpoint", false);
    model.result("pg2").feature("tlan1").set("anchorpoint", "center");
    model.result("pg2").run();

    model.param("par2").set("chi", "0.2");
    model.param("par2").set("D", "8e-10[m^2/s]");
    model.param("par3").set("stretch0", "3.215");
    model.param("par3").set("mu_ref", "0[Pa]");
    model.param("par3").set("stress", "0.05*Kmix/(stretch0^2)");
    model.param("par3").descr("stress", "\u6807\u79f0\u5e94\u529b");

    model.func().create("step1", "Step");
    model.func("step1").set("location", "0[s]");
    model.func("step1").set("locationdef", "beginning");

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(8);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "-stress*step1(t)"});
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(9);
    model.component("comp1").physics("dl").create("pr3", "Pressure", 1);
    model.component("comp1").physics("dl").feature("pr3").label("\u81ea\u7531\u6d41\u52a8\uff08\u56fa\u7ed3\uff09");
    model.component("comp1").physics("dl").feature("pr3").selection().set(8);
    model.component("comp1").physics("dl").feature("pr3").set("p0", "mu_ref");

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").create("map1", "Map");
    model.component("comp1").mesh("mesh2").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh2").feature("map1").feature("dis1").selection().set(7, 8);
    model.component("comp1").mesh("mesh2").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh2").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh2").feature("map1").feature("dis2").selection().set(6, 9);
    model.component("comp1").mesh("mesh2").feature("map1").feature("dis2")
         .set("numelem", "floor(height/elsize_c)+1");
    model.component("comp1").mesh("mesh2").create("map2", "Map");
    model.component("comp1").mesh("mesh2").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("map2").selection().set(1);
    model.component("comp1").mesh("mesh2").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh2").feature("map2").feature("dis1").selection().set(1, 5);
    model.component("comp1").mesh("mesh2").feature("map2").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/solid", true);
    model.study("std3").feature("time").setSolveFor("/physics/dl", true);
    model.study("std3").feature("time").setSolveFor("/physics/ge", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/poro1", true);
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u5355\u8f74\u56fa\u7ed3");
    model.study("std3").feature("time").set("tlist", "{0 1 2 5 10 20 50 100 200 1000}*(height/stretch0)^2/D");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledpair", new String[]{"p1"});
    model.study("std3").feature("time").set("disabledphysics", new String[]{"dl/pr1", "dl/pr2"});
    model.study("std3").feature("time").setSolveFor("/physics/ge", false);
    model.study("std3").feature("time").set("disabledphysics", new String[]{"dl/pr1", "dl/pr2", "ge"});
    model.study("std3").showAutoSequences("sol");

    model.sol("sol12").feature("v1").feature("comp1_mu").set("scalemethod", "manual");
    model.sol("sol12").feature("v1").feature("comp1_mu").set("scaleval", "1.0e5");
    model.sol("sol12").feature("v1").feature("comp1_u").set("scaleval", "height");
    model.sol("sol12").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol12").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol12").feature("t1").feature("dDef").set("linsolver", "pardiso");
    model.sol("sol12").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol12").feature("t1").feature("fc1").set("maxiter", 10);

    model.study("std3").createAutoSequences("all");

    model.sol("sol12").runAll();

    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(2);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").setIndex("looplevel", 10, 0);
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", "1");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").run();
    model.result("pg3").label("\u538b\u529b\u548c\u5316\u5b66\u52bf");
    model.result("pg3").setIndex("looplevel", 9, 0);
    model.result("pg3").set("view", "new");
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").set("displacementlinear", "absolute");
    model.result("pg3").set("celldisp", 0.4);
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").label("\u6df7\u5408\u7269\u5e94\u529b");
    model.result("pg3").feature("surf1").set("expr", "-poro1.sGpzz");
    model.result("pg3").feature("surf1").set("unit", "kPa");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("descr", "\u6b63\u5e94\u529b");
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").label("\u5316\u5b66\u52bf");
    model.result("pg3").feature("surf2").set("expr", "mu");
    model.result("pg3").feature("surf2").set("descr", "\u5316\u5b66\u52bf");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("arraydim", "1");
    model.result("pg3").feature("arws1").set("expr", new String[]{"dl.u", "dl.w"});
    model.result("pg3").feature("arws1").set("xnumber", 4);
    model.result("pg3").feature("arws1").set("ynumber", 20);
    model.result("pg3").feature("arws1").set("inheritplot", "surf1");
    model.result("pg3").feature("arws1").set("inheritarrowscale", false);
    model.result("pg3").feature("arws1").set("inheritcolor", false);
    model.result("pg3").feature("arws1").set("inheritrange", false);
    model.result("pg3").feature("arws1").set("manualindexing", true);
    model.result("pg3").feature("arws1").set("arrayindex", 1);
    model.result("pg3").feature("arws1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("tlan1", "TableAnnotation");
    model.result("pg3").feature("tlan1").set("arraydim", "1");
    model.result("pg3").feature("tlan1").set("source", "localtable");
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0.05, 0, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", -0.015, 0, 1);
    model.result("pg3").feature("tlan1")
         .setIndex("localtablematrix", "\\unicode{\u6b63\u5e94\u529b} $-\\sigma_{zz}$", 0, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0.45, 1, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", -0.015, 1, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "\\unicode{\u5316\u5b66\u52bf} $\\mu$", 1, 2);
    model.result("pg3").feature("tlan1").set("latexmarkup", true);
    model.result("pg3").feature("tlan1").set("showpoint", false);
    model.result("pg3").feature("tlan1").set("anchorpoint", "center");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3")
         .set("title", "\u8868\u9762\uff1a\u6b63\u5e94\u529b (kPa) \u8868\u9762\uff1a\u5316\u5b66\u52bf (kPa)\n\u9762\u4e0a\u7bad\u5934\uff1a\u603b\u8fbe\u897f\u901f\u5ea6\u573a\uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u539a\u5ea6\u4f38\u957f\u7387");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "Z/H");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\\lambda<sub>3</sub>");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", 0);
    model.result("pg4").set("ymin", 0.5);
    model.result("pg4").set("ymax", 3.5);
    model.result("pg4").set("manualgrid", true);
    model.result("pg4").set("xspacing", 0.2);
    model.result("pg4").set("yspacing", 0.5);
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(6);
    model.result("pg4").feature("lngr1").set("expr", "solid.stchp3*stretch0");
    model.result("pg4").feature("lngr1").set("descractive", true);
    model.result("pg4").feature("lngr1").set("descr", "\u7b2c\u4e09\u4e3b\u4f38\u957f\u7387");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "1-Z/height");
    model.result("pg4").feature("lngr1").set("colorcycle", "long");
    model.result("pg4").run();
    model.result("pg4").create("tlan1", "TableAnnotation");
    model.result("pg4").feature("tlan1").set("source", "localtable");
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 0, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 3.2, 0, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "$0^+$", 0, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.06, 1, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2.9, 1, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 1, 1, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.09, 2, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2.8, 2, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2, 2, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.14, 3, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2.7, 3, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 5, 3, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.2, 4, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2.6, 4, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 10, 4, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.28, 5, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2.5, 5, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 20, 5, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.43, 6, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2.2, 6, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 50, 6, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.59, 7, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "2.0", 7, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 100, 7, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.76, 8, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 1.5, 8, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "$Dt/H_0^2 = 200$", 8, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.8, 9, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.8, 9, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 1000, 9, 2);
    model.result("pg4").feature("tlan1").set("latexmarkup", true);
    model.result("pg4").feature("tlan1").set("showpoint", false);
    model.result("pg4").feature("tlan1").set("anchorpoint", "lowerleft");
    model.result("pg4").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u53c2\u8003\u6570\u636e\uff0cHong \u7b49\u4eba (2008)\uff0ct=2");
    model.result().table("tbl1").importData("hydrogel_swelling_uniaxial_t2.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("\u53c2\u8003\u6570\u636e\uff0cHong \u7b49\u4eba (2008)\uff0ct=20");
    model.result().table("tbl2").importData("hydrogel_swelling_uniaxial_t20.txt");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("\u53c2\u8003\u6570\u636e\uff0cHong \u7b49\u4eba (2008)\uff0ct=100");
    model.result().table("tbl3").importData("hydrogel_swelling_uniaxial_t100.txt");
    model.result("pg4").run();
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("linestyle", "none");
    model.result("pg4").feature("tblp1").set("linemarker", "circle");
    model.result("pg4").feature("tblp1").set("linecolor", "fromtheme");
    model.result("pg4").feature("tblp1").label("\u53c2\u8003\u6570\u636e\uff0cHong \u7b49\u4eba (2008)\uff0ct=2");
    model.result("pg4").feature().duplicate("tblp2", "tblp1");
    model.result("pg4").run();
    model.result("pg4").feature("tblp2").set("table", "tbl2");
    model.result("pg4").feature("tblp2").label("\u53c2\u8003\u6570\u636e\uff0cHong \u7b49\u4eba (2008)\uff0ct=20");
    model.result("pg4").feature().duplicate("tblp3", "tblp2");
    model.result("pg4").run();
    model.result("pg4").feature("tblp3").label("\u53c2\u8003\u6570\u636e\uff0cHong \u7b49\u4eba (2008)\uff0ct=100");
    model.result("pg4").feature("tblp3").set("table", "tbl3");
    model.result("pg4").run();

    model.param("par2").set("chi", "0.1");
    model.param("par2").set("D", "1e3[m^2/s]");
    model.param("par3").set("stretch0", "2");
    model.param("par3").set("mu_ref", "-2.6376e5[Pa]");

    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").setSolveFor("/physics/ge", false);
    model.study("std1").feature("time")
         .set("disabledphysics", new String[]{"ge", "solid/bndl1", "solid/roll1", "dl/pr3"});

    model.result("pg1").run();

    model.title("\u805a\u5408\u7269\u6c34\u51dd\u80f6\u7684\u9ad8\u5ea6\u6eb6\u80c0");

    model
         .description("\u805a\u5408\u7269\u6c34\u51dd\u80f6\u662f\u4e00\u79cd\u7531\u5438\u6536\u5927\u91cf\u6eb6\u5242\u5206\u5b50\u7684\u957f\u94fe\u805a\u5408\u7269\u4ea4\u8054\u7f51\u7edc\u6784\u6210\u7684\u6750\u6599\uff0c\u5e7f\u6cdb\u7528\u4e8e\u751f\u7269\u533b\u5b66\u9886\u57df\uff0c\u4f8b\u5982\u9776\u5411\u836f\u7269\u8f93\u9001\u3001\u7ec4\u7ec7\u5de5\u7a0b\u548c\u523a\u6fc0\u654f\u611f\u81f4\u52a8\u5668\u3002\u672c\u57fa\u51c6\u6a21\u578b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u5b9e\u65bd\u5927\u53d8\u5f62\u548c\u6eb6\u5242\u6269\u6563\u7684\u8026\u5408\u573a\u7406\u8bba\u6765\u6a21\u62df\u805a\u5408\u7269\u6c34\u51dd\u80f6\u7684\u9ad8\u5ea6\u6eb6\u80c0\u884c\u4e3a\u3002\u8be5\u7406\u8bba\u57fa\u4e8e Hong\u3001Zhao\u3001Zhou \u548c Suo \u4e8e 2008 \u5e74\u5728 JMPS 56:1779-1793 \u4e2d\u53d1\u8868\u7684\u5de5\u4f5c\u6210\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();

    model.label("hydrogel_swelling.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
