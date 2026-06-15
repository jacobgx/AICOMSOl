/*
 * simply_supported_composite_laminate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:36 by COMSOL 6.3.0.290. */
public class simply_supported_composite_laminate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");
    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a", "200[mm]", "\u8fb9\u957f");
    model.param().set("th", "a/4", "\u5c42\u5408\u677f\u539a\u5ea6");
    model.param().set("thl", "th/3", "\u8584\u5c42\u539a\u5ea6");
    model.param().set("E1", "25e6[psi]", "\u8584\u5c42\u6768\u6c0f\u6a21\u91cf\uff0c11 \u65b9\u5411");
    model.param().set("E2", "1e6[psi]", "\u8584\u5c42\u6768\u6c0f\u6a21\u91cf\uff0c22 \u65b9\u5411");
    model.param().set("E3", "E2", "\u8584\u5c42\u6768\u6c0f\u6a21\u91cf\uff0c33 \u65b9\u5411");
    model.param().set("G12", "0.5e6[psi]", "\u8584\u5c42\u526a\u5207\u6a21\u91cf\uff0c12 \u65b9\u5411");
    model.param().set("G23", "0.2e6[psi]", "\u8584\u5c42\u526a\u5207\u6a21\u91cf\uff0c23 \u65b9\u5411");
    model.param().set("G13", "G12", "\u8584\u5c42\u526a\u5207\u6a21\u91cf\uff0c13 \u65b9\u5411");
    model.param().set("nu12", "0.25", "\u8584\u5c42\u6cca\u677e\u6bd4\uff0c12 \u65b9\u5411");
    model.param().set("nu23", "nu12", "\u8584\u5c42\u6cca\u677e\u6bd4\uff0c23 \u65b9\u5411");
    model.param().set("nu13", "nu12", "\u8584\u5c42\u6cca\u677e\u6bd4\uff0c13 \u65b9\u5411");
    model.param().set("q0", "1[N/m^2]", "\u6a2a\u5411\u8f7d\u8377\u5e45\u503c");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("sdl", "q0*sin(pi*X/a)*sin(pi*Y/a)", "\u6b63\u5f26\u5206\u5e03\u7684\u6a2a\u5411\u8f7d\u8377");
    model.component("comp1").variable("var1")
         .set("SXX_lw", "lshell.SXX/q0*(th/a)^2", "\u9762\u5185\u6b63\u5e94\u529b (SXX)\uff0c\u5f52\u4e00\u5316");
    model.component("comp1").variable("var1")
         .set("SZZ_lw", "lshell.SZZ/q0", "\u6a2a\u5411\u6b63\u5e94\u529b (SZZ)\uff0c\u5f52\u4e00\u5316");
    model.component("comp1").variable("var1")
         .set("SYZ_lw", "lshell.SYZ/q0*th/a", "\u6a2a\u5411\u526a\u5207\u5e94\u529b (SYZ)\uff0c\u5f52\u4e00\u5316");
    model.component("comp1").variable("var1")
         .set("SXZ_lw", "lshell.SXZ/q0*th/a", "\u6a2a\u5411\u526a\u5207\u5e94\u529b (SXZ)\uff0c\u5f52\u4e00\u5316");
    model.component("comp1").variable("var1")
         .set("SXX_esl", "shell.SXX/q0*(th/a)^2", "\u9762\u5185\u6b63\u5e94\u529b (SXX)\uff0c\u5f52\u4e00\u5316");
    model.component("comp1").variable("var1")
         .set("SZZ_esl", "shell.SZZ/q0", "\u6a2a\u5411\u6b63\u5e94\u529b (SZZ)\uff0c\u5f52\u4e00\u5316");
    model.component("comp1").variable("var1")
         .set("SYZ_esl", "shell.SYZ/q0*th/a", "\u6a2a\u5411\u526a\u5207\u5e94\u529b (SYZ)\uff0c\u5f52\u4e00\u5316");
    model.component("comp1").variable("var1")
         .set("SXZ_esl", "shell.SXZ/q0*th/a", "\u6a2a\u5411\u526a\u5207\u5e94\u529b (SXZ)\uff0c\u5f52\u4e00\u5316");
    model.component("comp1").variable("var1").set("A", "1.105*a/2", "\u622a\u70b9\u5750\u6807");
    model.component("comp1").variable("var1").set("B", "1.894*a/2", "\u622a\u70b9\u5750\u6807");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "a/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1")
         .set("pos", new String[]{"a/2", "a/2"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "1");

    model.material().create("mat1", "Common", "");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").label("\u591a\u5c42\u6750\u6599\uff1a[0/90/0]");
    model.material("lmat1").setIndex("thickness", "thl", 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "thl", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", "0.0", 1);
    model.material("lmat1").setIndex("thickness", "thl", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", "0.0", 2);
    model.material("lmat1").setIndex("thickness", "thl", 2);
    model.material("lmat1").setIndex("meshPoints", 2, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", "0.0", 2);
    model.material("lmat1").setIndex("thickness", "thl", 2);
    model.material("lmat1").setIndex("meshPoints", 2, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("rotation", 90, 1);
    model.material("lmat1").setIndex("rotation", 0, 2);
    model.material("lmat1").set("widthRatio", 0.6);
    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");
    model.material("mat1").propertyGroup().create("Orthotropic", "Orthotropic", "Orthotropic");
    model.material("mat1").propertyGroup("Orthotropic").set("Evector", new String[]{"E1", "E2", "E3"});
    model.material("mat1").propertyGroup("Orthotropic").set("nuvector", new String[]{"nu12", "nu23", "nu13"});
    model.material("mat1").propertyGroup("Orthotropic").set("Gvector", new String[]{"G12", "G23", "G13"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"1"});

    model.component("comp1").physics("lshell").prop("ShapeProperty").set("order_displacement", "23s");
    model.component("comp1").physics("lshell").create("disp1", "Displacement", 1);
    model.component("comp1").physics("lshell").feature("disp1").selection().set(3);
    model.component("comp1").physics("lshell").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("lshell").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("lshell").feature().duplicate("disp2", "disp1");
    model.component("comp1").physics("lshell").feature("disp2").selection().set(4);
    model.component("comp1").physics("lshell").feature("disp2").setIndex("Direction", "free", 0);
    model.component("comp1").physics("lshell").feature("disp2").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("lshell").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("lshell").feature("sym1").selection().set(1, 2);
    model.component("comp1").physics("lshell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("lshell").feature("fl1").set("applyTo", "top");
    model.component("comp1").physics("lshell").feature("fl1").selection().set(1);
    model.component("comp1").physics("lshell").feature("fl1")
         .set("forceReferenceArea", new String[]{"0", "0", "sdl"});
    model.component("comp1").physics("shell").create("llem1", "LayeredElastic", 2);
    model.component("comp1").physics("shell").feature("llem1").selection().set(1);
    model.component("comp1").physics("shell").feature("llem1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("shell").feature("llem1").set("ShearCorrectionFactor", "BasedOn3DElasticity");
    model.component("comp1").physics("shell").create("ssp1", "SimplySupported", 1);
    model.component("comp1").physics("shell").feature("ssp1").selection().set(3, 4);
    model.component("comp1").physics("shell").feature("ssp1").set("PerpendicularEdge", false);
    model.component("comp1").physics("shell").create("sym1", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym1").selection().set(1, 2);
    model.component("comp1").physics("shell").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("shell").feature("fl1").selection().set(1);
    model.component("comp1").physics("shell").feature("fl1")
         .set("forceReferenceArea", new String[]{"0", "0", "sdl"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

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
    model.result().dataset().create("dset1shelllshl", "LayeredMaterial");
    model.result().dataset("dset1shelllshl").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1shelllshl");
    model.result("pg2").label("\u5e94\u529b (shell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg2").feature("surf1").set("inheritplot", "none");
    model.result("pg2").set("data", "dset1shelllshl");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("data", "dset1lshelllshl");
    model.result().dataset("mir1").set("quickx", "a/2");
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickplane", "zx");
    model.result().dataset("mir2").set("quicky", "a/2");
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").label("\u4e09\u7ef4\u622a\u70b9\uff1a(A, A)");
    model.result().dataset("cpt1").set("pointx", "A");
    model.result().dataset("cpt1").set("pointy", "A");
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").label("\u4e09\u7ef4\u622a\u70b9\uff1a(A, B)");
    model.result().dataset("cpt2").set("pointy", "B");
    model.result().dataset().duplicate("cpt3", "cpt2");
    model.result().dataset("cpt3").label("\u4e09\u7ef4\u622a\u70b9\uff1a(B, A)");
    model.result().dataset("cpt3").set("pointx", "B");
    model.result().dataset("cpt3").set("pointy", "A");
    model.result().dataset("cpt1").set("pointx", "A A B");
    model.result().dataset("cpt1").set("pointy", "A B A");

    model.component("comp1").view("view1").set("showgrid", true);

    model.result().dataset("cpt1").set("pointx", "A");
    model.result().dataset("cpt1").set("pointy", "A");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff08LW \u7406\u8bba\uff09\uff1a\u5b8c\u6574\u5c42\u5408\u677f");
    model.result("pg3").set("data", "mir2");
    model.result("pg3").set("edges", false);
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").feature("slc1").label("\u5207\u9762\uff08yz \u5e73\u9762\uff09");
    model.result("pg3").feature("slc1").set("expr", "lshell.mises");
    model.result("pg3").feature("slc1").set("quickxnumber", 3);
    model.result("pg3").feature("slc1").set("colortable", "RainbowLight");
    model.result("pg3").feature("slc1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("slc2", "slc1");
    model.result("pg3").run();
    model.result("pg3").feature("slc2").label("\u5207\u9762\uff08zx \u5e73\u9762\uff09");
    model.result("pg3").feature("slc2").set("titletype", "none");
    model.result("pg3").feature("slc2").set("colorlegend", false);
    model.result("pg3").feature("slc2").set("quickplane", "zx");
    model.result("pg3").feature("slc2").set("quickynumber", 3);
    model.result("pg3").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u8868\u683c\uff1aSXX");
    model.result().table("tbl1").importData("simply_supported_composite_laminate_stress_xx.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("\u8868\u683c\uff1aSZZ");
    model.result().table("tbl2").importData("simply_supported_composite_laminate_stress_zz.txt");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("\u8868\u683c\uff1aSYZ");
    model.result().table("tbl3").importData("simply_supported_composite_laminate_stress_yz.txt");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").label("\u8868\u683c\uff1aSXZ");
    model.result().table("tbl4").importData("simply_supported_composite_laminate_stress_xz.txt");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u9762\u5185\u6b63\u5e94\u529b (SXX)");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u9762\u5185\u6b63\u5e94\u529b (SXX)\uff0c\u5f52\u4e00\u5316");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u539a\u5ea6\u5750\u6807\uff0c\u5f52\u4e00\u5316");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("thr1", "ThroughThickness");
    model.result("pg4").feature("thr1").set("markerpos", "datapoints");
    model.result("pg4").feature("thr1").set("linewidth", "preference");
    model.result("pg4").feature("thr1").set("data", "cpt1");
    model.result("pg4").feature("thr1").set("expr", "SXX_lw");
    model.result("pg4").feature("thr1").set("descr", "\u9762\u5185\u6b63\u5e94\u529b (SXX)\uff0c\u5f52\u4e00\u5316");
    model.result("pg4").feature("thr1").set("ydata", "expr");
    model.result("pg4").feature("thr1").set("ydataexpr", "lshell.xd_rel");
    model.result("pg4").feature("thr1").set("includeinterfaces", "all");
    model.result("pg4").feature("thr1").set("legend", true);
    model.result("pg4").feature("thr1").set("legendmethod", "manual");
    model.result("pg4").feature("thr1").setIndex("legends", "\u5206\u5c42\u7406\u8bba", 0);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("thr2", "thr1");
    model.result("pg4").run();
    model.result("pg4").feature("thr2").set("expr", "SXX_esl");
    model.result("pg4").feature("thr2").set("descr", "\u9762\u5185\u6b63\u5e94\u529b (SXX)\uff0c\u5f52\u4e00\u5316");
    model.result("pg4").feature("thr2").set("titletype", "none");
    model.result("pg4").feature("thr2").set("ydataexpr", "shell.xd_rel");
    model.result("pg4").feature("thr2").set("includeinterfaces", false);
    model.result("pg4").feature("thr2").set("linestyle", "dashed");
    model.result("pg4").feature("thr2").setIndex("legends", "ESL \u7406\u8bba", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("linestyle", "none");
    model.result("pg4").feature("tblp1").set("linecolor", "red");
    model.result("pg4").feature("tblp1").set("linemarker", "circle");
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").feature("tblp1").set("legendmethod", "manual");
    model.result("pg4").feature("tblp1")
         .setIndex("legends", "\u53c2\u8003\u89e3\uff08\u7cbe\u786e\u7684\u4e09\u7ef4\u5f39\u6027\u89e3\uff09", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("tlan1", "TableAnnotation");
    model.result("pg4").feature("tlan1").set("source", "localtable");
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.6, 0, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.17, 0, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\\[0^\\circ \\,\\,\\textrm{Ply}\\]", 0, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.6, 1, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.5, 1, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\\[90^\\circ \\,\\,\\textrm{Ply}\\]", 1, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.6, 2, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.83, 2, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\\[0^\\circ \\,\\,\\textrm{Ply}\\]", 2, 2);
    model.result("pg4").feature("tlan1").set("latexmarkup", true);
    model.result("pg4").feature("tlan1").set("showpoint", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u6a2a\u5411\u6b63\u5e94\u529b (SZZ)");
    model.result("pg5").set("xlabel", "\u6a2a\u5411\u6b63\u5e94\u529b (SZZ)\uff0c\u5f52\u4e00\u5316");
    model.result("pg5").run();
    model.result("pg5").feature("thr1").set("expr", "SZZ_lw");
    model.result("pg5").feature("thr1").set("descr", "\u6a2a\u5411\u6b63\u5e94\u529b (SZZ)\uff0c\u5f52\u4e00\u5316");
    model.result("pg5").run();
    model.result("pg5").feature().remove("thr2");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("table", "tbl2");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6").label("\u6a2a\u5411\u526a\u5207\u5e94\u529b (SYZ)");
    model.result("pg6").set("xlabel", "\u6a2a\u5411\u526a\u5207\u5e94\u529b (SYZ)\uff0c\u5f52\u4e00\u5316");
    model.result("pg6").run();
    model.result("pg6").feature("thr1").set("data", "cpt2");
    model.result("pg6").feature("thr1").set("expr", "SYZ_lw");
    model.result("pg6").feature("thr1")
         .set("descr", "\u6a2a\u5411\u526a\u5207\u5e94\u529b (SYZ)\uff0c\u5f52\u4e00\u5316");
    model.result("pg6").run();
    model.result("pg6").feature("thr2").set("data", "cpt2");
    model.result("pg6").feature("thr2").set("expr", "SYZ_esl");
    model.result("pg6").feature("thr2")
         .set("descr", "\u6a2a\u5411\u526a\u5207\u5e94\u529b (SYZ)\uff0c\u5f52\u4e00\u5316");
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("table", "tbl3");
    model.result("pg6").run();
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", -0.02, 0, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", -0.02, 1, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", -0.02, 2, 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6a2a\u5411\u526a\u5207\u5e94\u529b (SXZ)");
    model.result("pg7").set("xlabel", "\u6a2a\u5411\u526a\u5207\u5e94\u529b (SXZ)\uff0c\u5f52\u4e00\u5316");
    model.result("pg7").set("legendpos", "center");
    model.result("pg7").run();
    model.result("pg7").feature("thr1").set("data", "cpt3");
    model.result("pg7").feature("thr1").set("expr", "SXZ_lw");
    model.result("pg7").feature("thr1")
         .set("descr", "\u6a2a\u5411\u526a\u5207\u5e94\u529b (SXZ)\uff0c\u5f52\u4e00\u5316");
    model.result("pg7").feature("thr1").set("includeinterfaces", false);
    model.result("pg7").run();
    model.result("pg7").feature("thr2").set("data", "cpt3");
    model.result("pg7").feature("thr2").set("expr", "SXZ_esl");
    model.result("pg7").feature("thr2")
         .set("descr", "\u6a2a\u5411\u526a\u5207\u5e94\u529b (SXZ)\uff0c\u5f52\u4e00\u5316");
    model.result("pg7").feature("thr2").set("includeinterfaces", "all");
    model.result("pg7").run();
    model.result("pg7").feature("tblp1").set("table", "tbl4");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").label("\u539a\u5ea6\u548c\u65b9\u5411 (lshell)");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"lshell.d"});
    model.result("pg8").feature("surf1").set("threshold", "manual");
    model.result("pg8").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg8").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").feature("surf1").label("\u539a\u5ea6");
    model.result("pg8").create("syss1", "CoordSysSurface");
    model.result("pg8").feature("syss1").set("mode", "matrix");
    model.result("pg8").feature("syss1").set("expr", "lshell.dsdX");
    model.result("pg8").label("\u539a\u5ea6\u548c\u65b9\u5411 (lshell)");
    model.result("pg8").run();

    model.component("comp1").view("view1").set("showgrid", true);

    model.result("pg3").run();

    model.title("\u7b80\u652f\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u7684\u5f2f\u66f2");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u53d7\u6b63\u5f26\u5206\u5e03\u7684\u6a2a\u5411\u8f7d\u8377\u4f5c\u7528\u7684\u7b80\u652f\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u7684\u5f2f\u66f2\u5206\u6790\u3002\u6b64\u5206\u6790\u91c7\u7528\u7684\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u8f83\u539a\uff0c\u5176\u539a\u5ea6\u4e0e\u8fb9\u957f\u4e4b\u6bd4\u4e3a 1:4\uff0c\u5e76\u4e14\u5305\u542b\u4e09\u5c42\u6b63\u4ea4\u94fa\u5c42\u3002\u8be5\u6a21\u578b\u57fa\u4e8e\u5206\u5c42 (LW) \u7406\u8bba\u548c\u7b49\u6548\u5355\u5c42 (ESL) \u7406\u8bba\u8fdb\u884c\u6c42\u89e3\u3002\n\n\u8fd9\u662f\u4e00\u4e2a\u9a8c\u8bc1\u793a\u4f8b\uff0c\u5176\u4e2d\u5c06\u57fa\u4e8e\u4e24\u79cd\u7406\u8bba\u8ba1\u7b97\u7684\u7ed3\u679c\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u7ed9\u51fa\u7684\u7cbe\u786e\u4e09\u7ef4\u5f39\u6027\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\uff0c\u5177\u4f53\u6765\u8bf4\uff0c\u5c06\u6bcf\u5c42\u7684\u9762\u5185\u6b63\u5e94\u529b\u3001\u6a2a\u5411\u6b63\u5e94\u529b\u548c\u6a2a\u5411\u526a\u5207\u5e94\u529b\u7684\u5168\u539a\u5ea6\u53d8\u5316\u4e0e\u53c2\u8003\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("simply_supported_composite_laminate.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
