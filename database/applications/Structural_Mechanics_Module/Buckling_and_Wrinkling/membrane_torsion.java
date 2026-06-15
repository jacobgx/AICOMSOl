/*
 * membrane_torsion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:01 by COMSOL 6.3.0.290. */
public class membrane_torsion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Buckling_and_Wrinkling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbrn", "StructuralMembrane", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mbrn", true);

    model.param().label("\u6a21\u578b\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("th", "1[m]", "\u5706\u7247\u539a\u5ea6");
    model.param().set("Rin", "5[m]", "\u5706\u7247\u5185\u534a\u5f84");
    model.param().set("Rout", "12.5[m]", "\u5706\u7247\u5916\u534a\u5f84");
    model.param().set("theta", "0[deg]", "\u65cb\u8f6c");
    model.param().set("rxx", "0", "\u4e2d\u95f4\u52a9\u53d8\u91cf");
    model.param().set("ryy", "0", "\u4e2d\u95f4\u52a9\u53d8\u91cf");
    model.param().set("rzz", "1", "\u4e2d\u95f4\u52a9\u53d8\u91cf");
    model.param().set("rx", "rxx/sqrt(rxx^2+ryy^2+rzz^2)", "\u4e2d\u95f4\u52a9\u53d8\u91cf");
    model.param().set("ry", "ryy/sqrt(rxx^2+ryy^2+rzz^2)", "\u4e2d\u95f4\u52a9\u53d8\u91cf");
    model.param().set("rz", "rzz/sqrt(rxx^2+ryy^2+rzz^2)", "\u4e2d\u95f4\u52a9\u53d8\u91cf");
    model.param().set("ct", "cos(theta)", "\u4e2d\u95f4\u52a9\u53d8\u91cf");
    model.param().set("st", "sin(theta)", "\u4e2d\u95f4\u52a9\u53d8\u91cf");
    model.param().set("R11", "ct+rx^2*(1-ct)", "\u65cb\u8f6c\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.param().set("R12", "rx*ry*(1-ct)-rz*st", "\u65cb\u8f6c\u77e9\u9635\uff0c12 \u5206\u91cf");
    model.param().set("R13", "rx*rz*(1-ct)+ry*st", "\u65cb\u8f6c\u77e9\u9635\uff0c13 \u5206\u91cf");
    model.param().set("R21", "rx*ry*(1-ct)+rz*st", "\u65cb\u8f6c\u77e9\u9635\uff0c21 \u5206\u91cf");
    model.param().set("R22", "ct+ry^2*(1-ct)", "\u65cb\u8f6c\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.param().set("R23", "rz*ry*(1-ct)-rx*st", "\u65cb\u8f6c\u77e9\u9635\uff0c23 \u5206\u91cf");
    model.param().set("R31", "rx*rz*(1-ct)-ry*st", "\u65cb\u8f6c\u77e9\u9635\uff0c31 \u5206\u91cf");
    model.param().set("R32", "rz*ry*(1-ct)+rx*st", "\u65cb\u8f6c\u77e9\u9635\uff0c32 \u5206\u91cf");
    model.param().set("R33", "ct+rz^2*(1-ct)", "\u65cb\u8f6c\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.param().create("par2");
    model.param("par2").label("\u5404\u5411\u540c\u6027\u6750\u6599\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("DD11_iso", "109.89[kPa]", "\u5f39\u6027\u77e9\u9635\uff0c11 \u5143\u7d20");
    model.param("par2").set("DD12_iso", "32.967[kPa]", "\u5f39\u6027\u77e9\u9635\uff0c12 \u5143\u7d20");
    model.param("par2").set("DD22_iso", "109.89[kPa]", "\u5f39\u6027\u77e9\u9635\uff0c22 \u5143\u7d20");
    model.param("par2").set("DD33_iso", "DD22_iso", "\u5f39\u6027\u77e9\u9635\uff0c33 \u5143\u7d20");
    model.param("par2").set("DD44_iso", "38.462[kPa]", "\u5f39\u6027\u77e9\u9635\uff0c44 \u5143\u7d20");
    model.param("par2").set("DD55_iso", "DD44_iso", "\u5f39\u6027\u77e9\u9635\uff0c55 \u5143\u7d20");
    model.param("par2").set("DD66_iso", "DD44_iso", "\u5f39\u6027\u77e9\u9635\uff0c66 \u5143\u7d20");
    model.param().create("par3");
    model.param("par3").label("\u6b63\u4ea4\u5404\u5411\u5f02\u6027\u6750\u6599\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("DD11_orth", "100.91[kPa]", "\u5f39\u6027\u77e9\u9635\uff0c11 \u5143\u7d20");
    model.param("par3").set("DD12_orth", "30.272[kPa]", "\u5f39\u6027\u77e9\u9635\uff0c12 \u5143\u7d20");
    model.param("par3").set("DD22_orth", "1009.1[kPa]", "\u5f39\u6027\u77e9\u9635\uff0c22 \u5143\u7d20");
    model.param("par3").set("DD33_orth", "DD22_orth", "\u5f39\u6027\u77e9\u9635\uff0c33 \u5143\u7d20");
    model.param("par3").set("DD44_orth", "38.5[kPa]", "\u5f39\u6027\u77e9\u9635\uff0c44 \u5143\u7d20");
    model.param("par3").set("DD55_orth", "DD44_orth", "\u5f39\u6027\u77e9\u9635\uff0c55 \u5143\u7d20");
    model.param("par3").set("DD66_orth", "DD44_orth", "\u5f39\u6027\u77e9\u9635\uff0c66 \u5143\u7d20");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").label("\u4e09\u89d2\u5f62\u7f51\u683c\uff0c\u6a21\u5f0f 1");
    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("filename", "membrane_torsion_tria_mesh1.mphbin");
    model.component("comp1").mesh("mesh1").feature("imp1").importData();
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh2").label("\u4e09\u89d2\u5f62\u7f51\u683c\uff0c\u6a21\u5f0f 2");
    model.component("comp1").mesh("mesh2").geometricModel("");
    model.component("comp1").mesh("mesh2").create("imp1", "Import");
    model.component("comp1").mesh("mesh2").feature("imp1").set("filename", "membrane_torsion_tria_mesh2.mphbin");
    model.component("comp1").mesh("mesh2").feature("imp1").importData();
    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh().create("mesh3");
    model.component("comp1").mesh("mesh3").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh3").label("\u56db\u8fb9\u5f62\u7f51\u683c\uff0c\u6a21\u5f0f 1");
    model.component("comp1").mesh("mesh3").geometricModel("");
    model.component("comp1").mesh("mesh3").create("imp1", "Import");
    model.component("comp1").mesh("mesh3").feature("imp1").set("filename", "membrane_torsion_quad_mesh1.mphbin");
    model.component("comp1").mesh("mesh3").feature("imp1").importData();
    model.component("comp1").mesh("mesh3").run();
    model.component("comp1").mesh().create("mesh4");
    model.component("comp1").mesh("mesh4").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh4").label("\u56db\u8fb9\u5f62\u7f51\u683c\uff0c\u6a21\u5f0f 2");
    model.component("comp1").mesh("mesh4").geometricModel("");
    model.component("comp1").mesh("mesh4").create("imp1", "Import");
    model.component("comp1").mesh("mesh4").feature("imp1").set("filename", "membrane_torsion_quad_mesh2.mphbin");
    model.component("comp1").mesh("mesh4").feature("imp1").importData();
    model.component("comp1").mesh("mesh4").run();

    model.component("comp1").physics("mbrn").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("mbrn").feature("lemm1").set("SolidModel", "Anisotropic");
    model.component("comp1").physics("mbrn").feature("lemm1").create("wr1", "Wrinkling", 2);
    model.component("comp1").physics("mbrn").feature("lemm1").feature("wr1").set("termination", "steporresi");
    model.component("comp1").physics("mbrn").feature("to1").set("d", "th");
    model.component("comp1").physics("mbrn").create("fix1", "Fixed", 1);
    model.component("comp1").physics("mbrn").feature("fix1").selection().set(2, 3, 7, 11);
    model.component("comp1").physics("mbrn").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("mbrn").feature("disp1").selection().set(4, 5, 8, 10);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("U0", "(R11-1)*X+R12*Y+R13*Z", 0);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("U0", "R21*X+(R22-1)*Y+R23*Z", 1);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("U0", "R31*X+R32*Y+(R33-1)*Z", 2);
    model.component("comp1").physics("mbrn").create("stb1", "MembraneStabilization", 2);
    model.component("comp1").physics("mbrn").create("disc1", "Discretization", -1);
    model.component("comp1").physics("mbrn").feature("disc1").set("order_displacement", 2);
    model.component("comp1").physics("mbrn").feature("disc1").label("\u4e8c\u6b21\u79bb\u6563\u5316");

    model.component("comp1").material().create("sw1", "Switch");
    model.component("comp1").material("sw1").feature().create("mat1", "Common", "comp1");
    model.component("comp1").material("sw1").feature("mat1").label("\u5404\u5411\u540c\u6027\u6750\u6599");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup()
         .create("Anisotropic", "Anisotropic", "Anisotropic");
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("Anisotropic")
         .set("D", new String[]{"DD11_iso", "DD12_iso", "DD22_iso", "0", "0", "DD33_iso", "0", "0", "0", "DD44_iso", 
         "0", "0", "0", "0", "DD55_iso", "0", "0", "0", "0", "0", 
         "DD66_iso"});
    model.component("comp1").material("sw1").feature("mat1").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("sw1").feature().create("mat2", "Common", "comp1");
    model.component("comp1").material("sw1").feature("mat2")
         .label("\u6b63\u4ea4\u5404\u5411\u5f02\u6027\u6750\u6599");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup()
         .create("Anisotropic", "Anisotropic", "Anisotropic");
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("Anisotropic")
         .set("D", new String[]{"DD11_orth", "DD12_orth", "DD22_orth", "0", "0", "DD33_orth", "0", "0", "0", "DD44_orth", 
         "0", "0", "0", "0", "DD55_orth", "0", "0", "0", "0", "0", 
         "DD66_orth"});
    model.component("comp1").material("sw1").feature("mat2").propertyGroup("def").set("density", new String[]{"0"});

    model.study("std1").label("\u7814\u7a76\uff1a\u4e09\u8282\u70b9\u4e09\u89d2\u5f62\uff08\u6a21\u5f0f 1\uff09");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("matsw", "MaterialSweep");
    model.study("std1").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std1").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "ct", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "ct", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "theta", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.5,10)", 0);
    model.study("std1").feature("stat").setIndex("punit", "deg", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("porder", "linear");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 100);
    model.sol("sol1").feature("s1").feature("fc1").set("stabacc", "aacc");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pm1").feature("so1").set("psol", "sol2");
    model.batch("pm1").run("compute");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u4e09\u8282\u70b9\u4e09\u89d2\u5f62\uff08\u6a21\u5f0f 2\uff09");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("matsw", "MaterialSweep");
    model.study("std2").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std2").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std2").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std2").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std2").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std2").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std2").feature("stat").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "ct", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "ct", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "theta", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.5,10)", 0);
    model.study("std2").feature("stat").setIndex("punit", "deg", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol5").feature("s1").feature("p1").set("porder", "linear");
    model.sol("sol5").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol5").feature("s1").feature("fc1").set("maxiter", 100);
    model.sol("sol5").feature("s1").feature("fc1").set("stabacc", "aacc");

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol6");
    model.sol("sol6").study("std2");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("pm2").feature("so1").set("psol", "sol6");
    model.batch("pm2").run("compute");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std3").label("\u7814\u7a76\uff1a\u56db\u8282\u70b9\u56db\u8fb9\u5f62\uff08\u6a21\u5f0f 3\uff09");
    model.study("std3").setGenPlots(false);
    model.study("std3").create("matsw", "MaterialSweep");
    model.study("std3").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std3").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std3").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std3").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std3").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std3").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std3").feature("stat").setEntry("mesh", "geom1", "mesh3");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "ct", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "", 0);
    model.study("std3").feature("stat").setIndex("pname", "ct", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "", 0);
    model.study("std3").feature("stat").setIndex("pname", "theta", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(0,0.5,10)", 0);
    model.study("std3").feature("stat").setIndex("punit", "deg", 0);
    model.study("std3").showAutoSequences("all");

    model.sol("sol9").feature("s1").feature("p1").set("porder", "linear");
    model.sol("sol9").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol9").feature("s1").feature("fc1").set("maxiter", 100);
    model.sol("sol9").feature("s1").feature("fc1").set("stabacc", "aacc");

    model.study("std3").createAutoSequences("all");

    model.sol().create("sol10");
    model.sol("sol10").study("std3");
    model.sol("sol10").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("pm3").feature("so1").set("psol", "sol10");
    model.batch("pm3").run("compute");

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std4").label("\u7814\u7a76\uff1a\u4e5d\u8282\u70b9\u56db\u8fb9\u5f62\uff08\u6a21\u5f0f 4\uff09");
    model.study("std4").setGenPlots(false);
    model.study("std4").create("matsw", "MaterialSweep");
    model.study("std4").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std4").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std4").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std4").feature("matsw").setIndex("pname", "matsw.comp1.sw1", 0);
    model.study("std4").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std4").feature("matsw").setIndex("plistarr", "range(1,1,2)", 0);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").setEntry("discretization", "mbrn", "disc1");
    model.study("std4").feature("stat").setEntry("mesh", "geom1", "mesh4");
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "ct", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "", 0);
    model.study("std4").feature("stat").setIndex("pname", "ct", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "", 0);
    model.study("std4").feature("stat").setIndex("pname", "theta", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "range(0,0.5,10)", 0);
    model.study("std4").feature("stat").setIndex("punit", "deg", 0);
    model.study("std4").showAutoSequences("all");

    model.sol("sol13").feature("s1").feature("p1").set("porder", "linear");
    model.sol("sol13").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol13").feature("s1").feature("fc1").set("maxiter", 100);
    model.sol("sol13").feature("s1").feature("fc1").set("stabacc", "aacc");

    model.study("std4").createAutoSequences("all");

    model.sol().create("sol14");
    model.sol("sol14").study("std4");
    model.sol("sol14").label("\u53c2\u6570\u5316\u89e3 4");

    model.batch("pm4").feature("so1").set("psol", "sol14");
    model.batch("pm4").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u8936\u76b1\u533a");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").set("edges", false);
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("arrayshape", "square");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("arraydim", "2");
    model.result("pg1").feature("surf1").set("expr", "mbrn.iswrinkled");
    model.result("pg1").feature("surf1").set("descr", "\u5e26\u8936\u76b1");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "none");
    model.result("pg1").feature("surf1").set("manualindexing", true);
    model.result("pg1").feature("surf1").set("rowindex", 1);
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("data", "dset4");
    model.result("pg1").feature("surf2").setIndex("looplevel", 1, 1);
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf2").set("rowindex", 0);
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").feature("surf3").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("data", "dset6");
    model.result("pg1").feature("surf3").set("rowindex", 1);
    model.result("pg1").feature("surf3").set("colindex", 1);
    model.result("pg1").feature().duplicate("surf4", "surf3");
    model.result("pg1").feature("surf4").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").set("data", "dset8");
    model.result("pg1").feature("surf4").set("rowindex", 0);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("titleparamindicator", false);

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "kPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg2").run();
    model.result("pg2").label("\u7b2c\u4e00\u4e3b\u5e94\u529b");
    model.result("pg2").set("legendpos", "rightdouble");
    model.result("pg2").feature("surf1").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "mbrn.sp1");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf2").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("expr", "mbrn.sp1");
    model.result("pg2").feature("surf2").set("inheritplot", "none");
    model.result("pg2").feature("surf2").set("colortable", "Prism");
    model.result("pg2").feature("surf3").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").set("expr", "mbrn.sp1");
    model.result("pg2").feature("surf3").set("inheritplot", "none");
    model.result("pg2").feature("surf3").set("colortable", "Prism");
    model.result("pg2").feature("surf4").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").set("expr", "mbrn.sp1");
    model.result("pg2").feature("surf4").set("inheritplot", "none");
    model.result("pg2").feature("surf4").set("colortable", "Prism");
    model.result("pg2").run();
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u7b2c\u4e8c\u4e3b\u5e94\u529b");
    model.result("pg3").feature("surf1").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "mbrn.sp2");
    model.result("pg3").feature("surf2").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("expr", "mbrn.sp2");
    model.result("pg3").feature("surf3").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").set("expr", "mbrn.sp2");
    model.result("pg3").feature("surf4").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf4").set("expr", "mbrn.sp2");
    model.result("pg3").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1")
         .label("\u6700\u5927\u8936\u76b1\u6d4b\u91cf\u503c\uff08\u5404\u5411\u540c\u6027\uff09");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "first", 1);
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").create("max1", "MaxSurface");
    model.result().evaluationGroup("eg1").feature("max1").selection().all();
    model.result().evaluationGroup("eg1").feature("max1").set("expr", new String[]{"mbrn.lemm1.wr1.Beta"});
    model.result().evaluationGroup("eg1").feature("max1")
         .set("descr", new String[]{"\u8936\u76b1\u6d4b\u91cf\uff0c\u6750\u6599\u5750\u6807\u7cfb"});
    model.result().evaluationGroup("eg1").feature("max1").set("unit", new String[]{"1"});
    model.result().evaluationGroup("eg1").feature().duplicate("max2", "max1");
    model.result().evaluationGroup("eg1").feature("max2").set("data", "dset4");
    model.result().evaluationGroup("eg1").feature("max2").setIndex("looplevelinput", "first", 1);
    model.result().evaluationGroup("eg1").feature("max2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("max3", "max2");
    model.result().evaluationGroup("eg1").feature("max3").set("data", "dset6");
    model.result().evaluationGroup("eg1").feature().duplicate("max4", "max3");
    model.result().evaluationGroup("eg1").feature("max4").set("data", "dset8");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").label("\u5404\u5411\u540c\u6027\u6750\u6599");
    model.nodeGroup().duplicate("grp2", "grp1");
    model.nodeGroup("grp2").label("\u6b63\u4ea4\u5404\u5411\u5f02\u6027\u6750\u6599");

    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").feature("surf2").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").setIndex("looplevel", 2, 1);
    model.result("pg4").feature("surf3").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").setIndex("looplevel", 2, 1);
    model.result("pg4").feature("surf4").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("surf4").setIndex("looplevel", 2, 1);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 2, 1);
    model.result("pg5").feature("surf2").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").setIndex("looplevel", 2, 1);
    model.result("pg5").feature("surf3").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").setIndex("looplevel", 2, 1);
    model.result("pg5").feature("surf4").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf4").setIndex("looplevel", 2, 1);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 2, 1);
    model.result("pg6").feature("surf2").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").setIndex("looplevel", 2, 1);
    model.result("pg6").feature("surf3").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").setIndex("looplevel", 2, 1);
    model.result("pg6").feature("surf4").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf4").setIndex("looplevel", 2, 1);
    model.result("pg6").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2")
         .label("\u6700\u5927\u8936\u76b1\u6d4b\u91cf\u503c\uff08\u6b63\u4ea4\u5404\u5411\u5f02\u6027\uff09");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg2").feature("max2").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg2").feature("max3").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg2").feature("max4").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg2").run();
    model.result("pg4").run();
    model.result().duplicate("pg7", "pg4");

    model.nodeGroup("grp2").add("plotgroup", "pg7");

    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").active(false);
    model.result("pg7").feature("surf3").active(false);
    model.result("pg7").feature("surf4").active(false);
    model.result("pg7").feature("surf1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("smooth", "material");
    model.result("pg7").run();
    model.result("pg7").create("arwl1", "ArrowLine");
    model.result("pg7").feature("arwl1").set("arraydim", "2");
    model.result("pg7").feature("arwl1").set("titletype", "none");
    model.result("pg7").feature("arwl1").set("expr", new String[]{"(R11-1)*X+R12*Y+R13*Z", "v", "w"});
    model.result("pg7").feature("arwl1").setIndex("expr", "R21*X+(R22-1)*Y+R23*Z", 1);
    model.result("pg7").feature("arwl1").setIndex("expr", "R31*X+R32*Y+(R33-1)*Z", 2);
    model.result("pg7").feature("arwl1").set("arrowcount", 20);
    model.result("pg7").feature("arwl1").set("scaleactive", true);
    model.result("pg7").feature("arwl1").set("scale", 4);
    model.result("pg7").feature("arwl1").create("sel1", "Selection");
    model.result("pg7").feature("arwl1").feature("sel1").selection().set(4, 5, 8, 10);
    model.result("pg7").run();
    model.result("pg7").set("data", "dset8");
    model.result("pg7").setIndex("looplevel", 1, 1);
    model.result("pg7").set("plotarrayenable", false);
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result("pg6").run();
    model.result("pg1").run();

    model.title("\u5706\u5f62\u819c\u7684\u626d\u8f6c");

    model
         .description("\u5728\u672c\u4f8b\u4e2d\uff0c\u5706\u73af\u5f62\u819c\u7684\u5185\u8fb9\u7f18\u53d7\u5230\u626d\u77e9\u4f5c\u7528\uff0c\u800c\u5916\u8fb9\u7f18\u5904\u4e8e\u56fa\u5b9a\u72b6\u6001\uff0c\u4ece\u800c\u5bfc\u81f4\u819c\u51fa\u73b0\u8936\u76b1\u3002\u8936\u76b1\u819c\u6a21\u578b\u53ef\u4ee5\u907f\u514d\u7531\u538b\u5e94\u529b\u4ea7\u751f\u7684\u5e73\u8861\u4e0d\u7a33\u5b9a\u6027\u3002\n\n\u672c\u4f8b\u7814\u7a76\u7f51\u683c\u56fe\u6848\u548c\u79bb\u6563\u9636\u6570\u5bf9\u8936\u76b1\u548c\u5e94\u529b\u5206\u5e03\u7684\u5f71\u54cd\u3002");

    model.component("comp1").mesh("mesh2").clearMesh();
    model.component("comp1").mesh("mesh3").clearMesh();
    model.component("comp1").mesh("mesh4").clearMesh();

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

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();

    model.label("membrane_torsion.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
